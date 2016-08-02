package mariana.service;

import mariana.entity.SickDay;
import mariana.entity.User;
import mariana.entity.VacantionDay;
import mariana.model.FreeDayModel;
import mariana.repository.SickDayRepository;
import mariana.repository.UserRepository;
import mariana.repository.VacantionDayRepository;
import mariana.util.Auth;
import mariana.util.FreeDayType;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mariana on 04.06.2016.
 */
@Service
@Transactional
public class FreeDayService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VacantionDayRepository vacantionDayRepository;

    @Autowired
    private SickDayRepository sickDayRepository;

    public void save(FreeDayModel freeDayModel){
        if(freeDayModel.getType().equals(FreeDayType.SICK_DAY)){
            saveSickDay(freeDayModel);
        }
        else{
            saveVacationDay(freeDayModel);
        }
    }

    private void saveSickDay(FreeDayModel freeDayModel){
        User user = userRepository.findByUsername(Auth.userLoggedIn());
        SickDay sickDay = new SickDay();
        sickDay.setDay(LocalDate.parse(freeDayModel.getDate(), DateTimeFormat.forPattern("dd/MM/yy")));
        sickDay.setUser(user);

        user.getEmployee().setSickDays(user.getEmployee().getSickDays() - 1);
        userRepository.save(user);

        sickDayRepository.save(sickDay);
    }

    private void saveVacationDay(FreeDayModel freeDayModel){
        User user = userRepository.findByUsername(Auth.userLoggedIn());
        VacantionDay vacationDay = new VacantionDay();
        vacationDay.setDay(LocalDate.parse(freeDayModel.getDate(), DateTimeFormat.forPattern("dd/MM/yy")));
        vacationDay.setUser(user);

        user.getEmployee().setVacantionDays(user.getEmployee().getVacantionDays() - 1);
        userRepository.save(user);

        vacantionDayRepository.save(vacationDay);
    }
}
