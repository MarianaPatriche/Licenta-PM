package mariana.model;

import mariana.util.FreeDayType;

/**
 * Created by mariana on 04.06.2016.
 */
public class FreeDayModel {

    private String date;

    private FreeDayType type;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public FreeDayType getType() {
        return type;
    }

    public void setType(FreeDayType type) {
        this.type = type;
    }

    public FreeDayModel(String date, FreeDayType type) {
        this.date = date;
        this.type = type;
    }

    public FreeDayModel() {
    }
}
