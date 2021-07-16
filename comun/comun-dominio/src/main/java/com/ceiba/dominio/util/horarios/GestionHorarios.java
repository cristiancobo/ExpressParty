package com.ceiba.dominio.util.horarios;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GestionHorarios {

    private int anio;
    private int mes;
    private int dia;
    private ArrayList<String> listaDiasFestivosColombia;

    public GestionHorarios() {

        Date date = new Date();
        SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
        String anioActual = getYearFormat.format(date);
        this.anio = Integer.parseInt(anioActual);
        this.listaDiasFestivosColombia = new ArrayList<>();

        int a = anio % 19;
        int b = anio / 100;
        int c = anio % 100;
        int d = b / 4;
        int e = b % 4;
        int g = (8 * b + 13) / 25;
        int h = (19 * a + b - d - g + 15) % 30;
        int j = c / 4;
        int k = c % 4;
        int m = (a + 11 * h) / 319;
        int r = (2 * e + 2 * j - k - h + m + 32) % 7;

        this.mes = (h - m + r + 90) / 25;
        this.dia = (h - m + r + this.mes + 19) % 32;
        this.mes--;

        this.listaDiasFestivosColombia.add("0:1");               //Primero de Enero
        this.listaDiasFestivosColombia.add("4:1");               //Día del trabajo 1 de mayo
        this.listaDiasFestivosColombia.add("6:20");              //Independencia 20 de Julio
        this.listaDiasFestivosColombia.add("7:7");               //Batalla de boyaca 7 de agosto
        this.listaDiasFestivosColombia.add("11:8");              //Maria inmaculada 8 de diciembre
        this.listaDiasFestivosColombia.add("11:25");             //Navidad 25 de diciembre

        this.calcularEmiliani(0, 6);           		 // Reyes magos 6 de enero
        this.calcularEmiliani(2, 19);       			 //San jose 19 de marzo
        this.calcularEmiliani(5, 29);    		     //San pedro y san pablo 29 de junio
        this.calcularEmiliani(7, 15); 			     //Asuncion 15 de agosto
        this.calcularEmiliani(9, 12);  				 //Descubrimiento de america 12 de octubre
        this.calcularEmiliani(10, 1); 			     //Todos los santos 1 de noviembre
        this.calcularEmiliani(10, 11);    			 //Independencia de cartagena 11 de noviembre

        this.calcularOtroFestivo(-3, false);  		 //jueves santos
        this.calcularOtroFestivo(-2, false); 		 //viernes santo
        this.calcularOtroFestivo(40, true);		 //Asención del señor de pascua
        this.calcularOtroFestivo(60, true); 		 //Corpus cristi
        this.calcularOtroFestivo(68, true); 		 //Sagrado corazon



    }

    private void calcularEmiliani(int month, int day) {
        Calendar date = Calendar.getInstance();
        date.set(this.anio, month, day);
        int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case 1:
                date.add(Calendar.DATE, 1);
                break;
            case 3:
                date.add(Calendar.DATE, 6);
                break;
            case 4:
                date.add(Calendar.DATE, 5);
                break;
            case 5:
                date.add(Calendar.DATE, 4);
                break;
            case 6:
                date.add(Calendar.DATE, 3);
                break;
            case 7:
                date.add(Calendar.DATE, 2);
                break;
            default:
                break;
        }
        this.listaDiasFestivosColombia.add(date.get(Calendar.MONTH) + ":" + date.get(Calendar.DATE));
    }

    private void calcularOtroFestivo(int days, boolean emiliani) {
        Calendar date = Calendar.getInstance();
        date.set(this.anio, this.mes, this.dia);
        date.add(Calendar.DATE, days);
        if (emiliani) {
            this.calcularEmiliani(date.get(Calendar.MONTH), date.get(Calendar.DATE));
        } else {
            this.listaDiasFestivosColombia.add(date.get(Calendar.MONTH) + ":" + date.get(Calendar.DATE));
        }
    }

    public boolean esFestivo(int month, int day) {
        return this.listaDiasFestivosColombia.contains(month + ":" +day);
    }
    public int getAnio() {
        return anio;
    }

    public LocalDateTime calcularSiguienteDiaHabil(Date fecha, int diasReserva) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        GestionHorarios fechaFestivos = new GestionHorarios();
        while(diasReserva > 0) {
            calendario.add(Calendar.DATE, 1);
            if (calendario.get(Calendar.YEAR) != fechaFestivos.getAnio()) {
                fechaFestivos = new GestionHorarios();
            }
            int dayOfWeek = calendario.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek != 1 && dayOfWeek != 7 && !fechaFestivos.esFestivo(calendario.get(Calendar.MONTH), calendario.get(Calendar.DATE))) {
                diasReserva--;
            }
        }
        Date diaHabil = calendario.getTime();
        SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter diaHabilConFormato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return  LocalDateTime.parse(formato.format(diaHabil), diaHabilConFormato);
    }
}
