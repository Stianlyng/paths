package edu.ntnu.g60.utils.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ntnu.g60.entities.ActionEntity;
import edu.ntnu.g60.entities.GoalEntity;
import edu.ntnu.g60.entities.LinkEntity;
import edu.ntnu.g60.entities.PassageEntity;
import edu.ntnu.g60.entities.StoryEntity;
import edu.ntnu.g60.exceptions.BrokenLinkException;
import edu.ntnu.g60.exceptions.StoryNotFoundException;
import edu.ntnu.g60.models.actions.Action;
import edu.ntnu.g60.models.actions.ActionFactory;
import edu.ntnu.g60.models.goals.Goal;
import edu.ntnu.g60.models.goals.GoldGoal;
import edu.ntnu.g60.models.goals.HealthGoal;
import edu.ntnu.g60.models.goals.InventoryGoal;
import edu.ntnu.g60.models.goals.ScoreGoal;
import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.passage.Passage;
import edu.ntnu.g60.models.passage.PassageBuilder;
import edu.ntnu.g60.models.story.Story;
import edu.ntnu.g60.models.story.StoryBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * StoryParser is a class that parses a JSON file into a Story object.
 *
 * @author Stian Lyng
 */
public class StoryParser {
  
  /**
   * The logger for this class.
   */
  private static final Logger LOGGER = Logger.getLogger(StoryParser.class.getName());

  /**
   * The JSON file to be parsed.
   */
  private final InputStream jsonFile;

  /**
   * The ObjectMapper used to parse the JSON file.
   */
  private final ObjectMapper objectMapper;

  /**
   * The Story object that is being built.
   */
  private Story story;

  /**
   * The list of goals to be fulfilled in the story.
   */
  private List<Goal> goals;

  /**
   * The path to the JSON file.
   */
  public static final String STORY_PATH = "/stories/";

  /**
   * Constructs a StoryParser object.
   *
   * @param jsonFileName the name the JSON file to be parsed.
   * @throws StoryNotFoundException if the JSON file is not found.
   */
  public StoryParser(String jsonFilename) {
    this.jsonFile = StoryParser.class.getResourceAsStream(STORY_PATH + jsonFilename + ".json");
    if (this.jsonFile == null) {
      LOGGER.severe("Could not find file: " + jsonFilename);
      throw new StoryNotFoundException("Could not find file: " + jsonFilename);
    }
    this.objectMapper = new ObjectMapper();
    LOGGER.info("Building story from JSON file");
    build();
  }

  /**
   * Builds a Story object from a JSON file.
   *
   * @param jsonStream
   */
  public StoryParser(InputStream jsonStream) {
    this.jsonFile = jsonStream;
    if (this.jsonFile == null) {
      LOGGER.severe("Could not find file: " + jsonStream);
      throw new StoryNotFoundException("inputstream is null: " + jsonStream);
    }
    this.objectMapper = new ObjectMapper();
    LOGGER.info("Building story from inputstream");
    build();
  }

  /**
   * Builds a Passage object from a PassageEntity object.
   *
   * @param passageEntity the PassageEntity object to be parsed.
   * @return a Passage object.
   */
  private Passage buildPassage(PassageEntity passageEntity) {
    LOGGER.info("Building passage: " + passageEntity.getTitle());
    Passage passage = new PassageBuilder()
      .setTitle(passageEntity.getTitle())
      .setContent(passageEntity.getContent())
      .setBackgroundImage(passageEntity.getBackground())
      .setPlayerImage(passageEntity.getPlayer())
      .setEnemyImage(passageEntity.getEnemy())
      .isFightScene(passageEntity.getIsFight())
      .build();

    passageEntity
      .getLinks()
      .forEach(linkEntity -> {
        passage.addLink(buildLink(linkEntity));
      });

    return passage;
  }

  /**
   * Builds a Link object from a LinkEntity object.
   *
   * @param linkEntity the LinkEntity object to be parsed.
   * @return a Link object.
   */
  private Link buildLink(LinkEntity linkEntity) {
    LOGGER.info("Building link: " + linkEntity.getText());
    Link link = new Link(linkEntity.getText(), linkEntity.getReference());

    linkEntity
      .getActions()
      .forEach(actionEntity -> {
        Action action = buildAction(actionEntity);
        link.addAction(action);
      });

    return link;
  }

  /**
   * Builds an Action object from an ActionEntity object.
   * @param actionEntity the ActionEntity object to be parsed.
   * @return an Action object.
   */
  private Action buildAction(ActionEntity actionEntity) {
    Action action = ActionFactory.createAction(actionEntity);
    LOGGER.info("Building action: " + action.getClass().getSimpleName());
    return action;
  }

  /**
   * Builds the Story object from the JSON file.
   *
   * @return a Story object.
   */
  private void build() {
    try {
      StoryEntity storyEntity = objectMapper.readValue(jsonFile, StoryEntity.class);
      StoryBuilder storyBuilder = new StoryBuilder().setTitle(storyEntity.getTitle());
      LOGGER.info("Building story: " + storyEntity.getTitle());

      storyEntity
        .getPassages()
        .forEach(passageEntity -> {
          Passage passage = buildPassage(passageEntity);
          storyBuilder.addPassage(passage);
          if (storyEntity.getPassages().indexOf(passageEntity) == 0) {
            storyBuilder.setOpeningPassage(passage);
          }
        });
      this.story = storyBuilder.build();

      GoalEntity goalEntity = storyEntity.getGoals();
      this.goals = buildGoals(goalEntity);

      System.out.println("Goals: " + this.goals.toString());
    } catch (IOException e) {
      LOGGER.severe("Error reading JSON file: " + e.getMessage());
      throw new RuntimeException("Error reading JSON file", e);
    }
  }

  /**
   * Builds a list of goals from a GoalEntity object.
   * @param goalEntity the GoalEntity object to be parsed.
   * @return a list of goals.
   */
  private List<Goal> buildGoals(GoalEntity goalEntity) {
    if (goalEntity == null) goalEntity = new GoalEntity();
    LOGGER.info("Building goals");

    return List.of(
      new GoldGoal(goalEntity.getGold()),
      new HealthGoal(goalEntity.getHealth()),
      new InventoryGoal(goalEntity.getInventory()),
      new ScoreGoal(goalEntity.getScore())
    );
  }

  /**
   * Returns the story object, and checks if there are any broken links in the story.
   *
   * @return the story object.
   * @throws BrokenLinkException if there are any broken links in the story.
   */
  public Story getStory() throws BrokenLinkException {
    List<Link> brokenLinks = story.getBrokenLinks();
    if (!brokenLinks.isEmpty()) {
      String brokenLinkTitles = brokenLinks
        .stream()
        .map(Link::getText)
        .distinct()
        .collect(Collectors.joining(", \n"));
      LOGGER.warning("There are broken links in the story: " + brokenLinkTitles);
      throw new BrokenLinkException("There are broken links in the story: " + brokenLinkTitles);
    }
    return this.story;
  }

  public List<Goal> getGoals() {
    return this.goals;
  }
}
