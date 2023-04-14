package edu.ntnu.g60;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

public class Save implements Externalizable{
    public Passage passage;
    public String saveName;
    public int saveNumber;
    
    public Save(Passage passage, String saveName, int saveNumber){
        this.saveNumber = saveNumber;
        this.passage = passage;
        this.saveName = saveName;
        if (passage == null) throw new IllegalArgumentException("Passage is empty");
    }

    public Save(){
    }

    public String getSaveName(){
        return saveName;
    }

    public Passage getPassage(){
        return passage;
    }

    public int getSaveNumber(){
        return saveNumber;
    }



    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        String title = passage.getTitle();
        String contect = passage.getContent();
        List<Link> links = passage.getLinks();
        String linkRef1 = links.get(0).getReference();
        String linkRef2 = links.get(1).getReference();
        String linkTxt1 = links.get(0).getText();
        String linkTxt2 = links.get(1).getText();

        out.writeUTF(title);
        out.writeUTF(contect);
        out.writeUTF(saveName);
        out.writeUTF(linkRef1);
        out.writeUTF(linkRef2);
        out.writeUTF(linkTxt1);
        out.writeUTF(linkTxt2);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        String title = in.readUTF();
        String content = in.readUTF();
        saveName = in.readUTF();
        passage = new Passage(title, content);

        String linkRef1 = in.readUTF();
        String linkRef2 = in.readUTF();
        String linkTxt1 = in.readUTF();
        String linkTxt2 = in.readUTF();

        passage.addLink(new Link(linkTxt1, linkRef1));
        passage.addLink(new Link(linkTxt2, linkRef2));
    }



}
