import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupID;
    private List<String> members;

    public Group(String groupID) {
        this.groupID = groupID;
        this.members = new ArrayList<String>();
    }

    public String getGroupID() {
        return groupID;
    }

    public boolean isGroup() {
        return true;
    }

    public List<String> getMembers() {
        return members;
    }

    public void addMember(String member) {
        members.add(member);
    }

}
