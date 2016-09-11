package mariana.util;

/**
 * Created by mariana on 02.06.2016.
 */
public enum ProjectStatus {
    STARTED, ENDED, DELAYED, MAINTENANCE;

    @Override
    public String toString() {
        switch (this){
            case STARTED:
                return "Inceput";
            case ENDED:
                return "Finalizat";
            case DELAYED:
                return "Intarziat";
            case MAINTENANCE:
                return "Mentenanta";
            default:
                return "Nu s-a gasit status!";
        }
}}
