package family_tree.family_tree;

import family_tree.human.Human;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FamilyTree implements Serializable {
    private long humansId;
    private List<Human> humanList;

    public FamilyTree() {
        this(new ArrayList<>());
    }

    public FamilyTree(ArrayList<Human> humanList) {
        this.humanList = humanList;
    }

    public boolean add(Human human){
        if (human == null){
            return false;
        }
        if (!humanList.contains(human)){
            humanList.add(human);
            human.setId(humansId++);

            addToParents(human);
            addToChildren(human);

            return true;
        }
        return false;
    }

    public void addToParents(Human human){
        for (Human parent: human.getParents()){
            parent.addChild(human);
        }
    }

    public void addToChildren(Human human){
        for (Human child: human.getChildren()){
            child.addParent(human);
        }
    }

    public List<Human> searchByFullName(String surname, String name, String middleName){
        List<Human> res = new ArrayList<>();
        for (Human human: humanList){
            if (human.getSurname().equals(surname) && human.getName().equals(name) && human.getMiddleName().equals(middleName)){
                res.add(human);
            }
        }
        return res;
    }

    public boolean setWedding(Human human1, Human human2){
        if (human1.getSpouse() == null && human2.getSpouse() == null){
            human1.setSpouse(human2);
            human2.setSpouse(human1);
            return true;
        }
        return false;
    }

    public boolean setDivorce(Human human1, Human human2){
        if (human1.getSpouse() != null && human2.getSpouse() != null){
            human1.setSpouse(null);
            human2.setSpouse(null);
            return true;
        }
        return false;
    }

    public boolean remove(long humansId){
        if (checkId(humansId)){
            Human human = getById(humansId);
            return humanList.remove(human);
        }
        return false;
    }

    private boolean checkId(long id){
        return id < humansId && id >= 0;
    }

    public Human getById(long id){
        for (Human human: humanList){
            if (human.getId() == id){
                return human;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return getInfo();
    }

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("В семейном древе " + humanList.size() + " объектов:\n");
        for (Human human : humanList) {
            sb.append("---------------------------------------------\n");
            sb.append(human);
            sb.append("\n");
        }
        return sb.toString();
    }
}
