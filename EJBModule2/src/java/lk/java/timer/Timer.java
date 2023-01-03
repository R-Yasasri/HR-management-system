/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.java.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import lk.java.entity.Attendance;
import lk.java.entity.Employee;
import lk.java.session.AttendanceBeanLocal;
import lk.java.session.SendMailBeanRemote;

/**
 *
 * @author Rajitha Yasasri
 */
@Stateless
@LocalBean
public class Timer {

    @EJB
    private SendMailBeanRemote sendMailBean;

    @EJB
    private AttendanceBeanLocal attendanceBean;

    @Schedule(dayOfWeek = "Mon-Fri", month = "*", hour = "*", dayOfMonth = "*", year = "*", minute = "*", second = "0")

    public void myTimer() {
        //System.out.println("Timer event: " + new Date());

        Date date = new Date();

        try {
            List<Attendance> attendanceByDate = attendanceBean.getAttendanceByDate(date);
            //generateReport(attendanceByDate, date);
            System.out.println("report sent");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateReport(List attendanceByDate, Date date) {

        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf1.format(date);

            String document = "";
            String prolog = "<!DOCTYPE html>";
            String head = "<html><head>\n"
                    + "<style type=\"text/css\">\n"
                    + "	th,td{\n"
                    + "		padding: 5px 10px;\n"
                    + "	}\n"
                    + "</style>\n"
                    + "	</head>";
            String bodyTag = "<body>";
            String header = "<h2>Attendance Report</h2><p>date: " + format + "</p>";
            String table = "<table border='1' style='border-collapse: collapse;'><tr><th>Employee id</th><th>Employee Name</th><th>Shift</th><th>Clock In</th><th>Clock Out</th></tr>";

            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            for (int i = 0; i < attendanceByDate.size(); i++) {

                Attendance attendance = (Attendance) attendanceByDate.get(i);

                Employee emp = attendance.getShiftIdshift().getEmployeeIdemployee();

                String cIn = sdf.format(attendance.getClockIn());
                String cOut = sdf.format(attendance.getClockOut());

                table += "<tr><td>" + emp.getIdemployee() + "</td><td>" + emp.getName() + "</td><td>" + attendance.getShiftIdshift().getIdshift() + "</td><td>" + cIn + "</td><td>" + cOut + "</td></tr>";
            }

            document += prolog + head + bodyTag + header + table + "</table></body></html>";
//        System.out.println(document);

            sendMailBean.sendHTMLMail("rajithayasssri@gmail.com", "Attendance Report", document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
