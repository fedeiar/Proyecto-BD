package parquimetros;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class Fecha {
    
	
    //metodos
    public static java.sql.Date getFechaActualSQL(){
        Date fecha = new Date();
        java.sql.Date fechaSQL = java.sql.Date.valueOf((new SimpleDateFormat("yyyy-MM-dd")).format(fecha));
        return fechaSQL;
    }

    public static java.sql.Time getHoraActualSQL(){
        Date fecha = new Date();
        Time timeSQL = java.sql.Time.valueOf((new SimpleDateFormat("HH:mm:ss")).format(fecha));
        return timeSQL;
    }

	public static String getDiaActual() {
        Calendar cal = Calendar.getInstance();
        int dia_del_mes_int = cal.get(Calendar.DAY_OF_WEEK);

	    switch(dia_del_mes_int) {
	        case(1): {return "Do";}
	    	case(2): {return "Lu";}
	    	case(3): {return "Ma";}
	    	case(4): {return "Mi";}
	    	case(5): {return "Ju";}
	    	case(6): {return "Vi";}
	    	case(7): {return "Sa";}
	    }
	    return null;
	}

	public static char getTurno() {
        Date fecha = new Date();
        int hora = Integer.parseInt(new SimpleDateFormat("H").format(fecha));
        
        System.out.println(hora);
	    if(hora >= 8 && hora < 14) 
	    	return 'm';
	    else 
	    	if(hora >= 14 && hora <= 20) 
	    		return 't';
	    	
	    return 'f'; //fuera de horario 
	}
	
}

