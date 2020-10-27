package parquimetros;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Fecha {
    
	
    //metodos
    public static java.sql.Date getFechaActualSQL(){
        Date fecha = new Date();
        java.sql.Date fechaSQL = java.sql.Date.valueOf((new SimpleDateFormat("yyyy-MM-dd")).format(fecha));;
        return fechaSQL;
    }

    public static java.sql.Time getHoraActualSQL(){
        Date fecha = new Date();
        Time timeSQL = java.sql.Time.valueOf((new SimpleDateFormat("HH:mm:ss")).format(fecha));
        return timeSQL;
    }

	public static String getDiaActual() {
        Date fecha = new Date();

	    switch(new SimpleDateFormat("E").format(fecha)) {
	        case("Monday"): {return "Lu";}
	    	case("Tuesday"): {return "Ma";}
	    	case("Wednesday"): {return "Mi";}
	    	case("Thursday"): {return "Ju";}
	    	case("Friday"): {return "Vi";}
	    	case("Saturday"): {return "Sa";}
	    	case("Sunday"): {return "Do";}
	    }
	    return null;
	}

	public static char getTurno() {
        Date fecha = new Date();

	    int hora = Integer.parseInt(new SimpleDateFormat("H").format(fecha));
	    if(hora >= 8 && hora < 14) 
	    	return 'm';
	    else 
	    	if(hora >= 14 && hora <= 20) 
	    		return 't';
	    	
	    return 'f'; //fuera de horario 
	}
	
}

