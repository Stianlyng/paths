# Paths Adventure Game

A JavaFX maven prosject made as a semesterproject in Programming 2 at NTNU

## Semester project in Programming 2 at NTNU
By: Stian Lyng & Olav Sie Rotvær

## Links
- **Jacoco:** https://gruppe_60.pages.stud.idi.ntnu.no/paths/jacoco/index.html
- **JavaDoc:** https://gruppe_60.pages.stud.idi.ntnu.no/paths/apidocs/index.html

## Install
1. Clone Paths:

Using SSH:
`git@gitlab.stud.idi.ntnu.no:gruppe_60/paths.git`

Using HTTPS:
`https://gitlab.stud.idi.ntnu.no/gruppe_60/paths.git`

2. install dependencies and create JAR.

`mvn clean install`

3. Run the JAR or run the application directly.

`mvn javafx:run`

## Usage

If you want to add custom stories, it kan be imported using the importer inside the game. The format of the textfile should be:

```txt
Title Of the Story

:: Passage Title
The Content you would like to present
[The text of a link](Reference to the other passage, by its title)
```

Or you can create a story with custom styling and actions by placing a json file inside the `resources/stories` directory. The format of the json file should be:

```json
{
  "title": "The Title of the Story",
  "passages": [
    {
      "title": "First Passage",
      "content": "This is the first passage.",
      "background": "background.jpg",
      "player": "player.png",
      "enemy": "enemy.png",
      "isFight": false,
      "links": [
        {
          "text": "Go to the next passage",
          "reference": "Second Passage",
          "actions": [
            {
              "type": "HEALTH",
              "value": 100
            },
            {
              "type": "GOLD",
              "value": 100
            },
            {
              "type": "SCORE",
              "value": 100
            }
          ]
        }
      ]
    }
    ]
}
```