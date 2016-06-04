package mariana.model;

import java.util.List;

/**
 * Created by mariana on 04.06.2016.
 */
public class WorkList {
    private List<WorkDayModel> list;

    public List<WorkDayModel> getList() {
        return list;
    }

    public void setList(List<WorkDayModel> list) {
        this.list = list;
    }

    public WorkList(List<WorkDayModel> list) {
        this.list = list;
    }

    public WorkList() {
    }
}

