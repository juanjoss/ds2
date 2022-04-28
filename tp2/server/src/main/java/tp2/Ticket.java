/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp2;

import java.sql.Date;

public class Ticket {
    private String from;
    private String to;
    private Date departure_date;
    private Date return_date;
    private int remaining_spots;
    private String company_name;
    
    public Ticket(String from, String to, Date departure_date, Date return_date, int remaining_spots, String company_name){
        this.from = from;
        this.to = to;
        this.departure_date = departure_date;
        this.return_date = return_date;
        this.remaining_spots = remaining_spots;
        this.company_name = company_name;
    }
    
    public Ticket(String from, String to, Date departure_date, int remaining_spots, String company_name){
        this.from = from;
        this.to = to;
        this.departure_date = departure_date;
        this.return_date = null;
        this.remaining_spots = remaining_spots;
        this.company_name = company_name;
    }
    
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(Date departure_date) {
        this.departure_date = departure_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public int getRemaining_spots() {
        return remaining_spots;
    }

    public void setRemaining_spots(int remaining_spots) {
        this.remaining_spots = remaining_spots;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_names) {
        this.company_name = company_names;
    }
}
