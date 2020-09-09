// 
// Decompiled by Procyon v0.5.36
// 

package fallcraftsystem.utils;

import java.util.Date;

public class TimeCalculator
{
    public static double diferencaEmDias(final Date dataInicial, final Date dataFinal) {
        double result = 0.0;
        final long diferenca = dataFinal.getTime() - dataInicial.getTime();
        final double diferencaEmDias = (double)(diferenca / 1000L / 60L / 60L / 24L);
        final long horasRestantes = diferenca / 1000L / 60L / 60L % 24L;
        result = diferencaEmDias + horasRestantes / 24.0;
        return result;
    }
    
    public static double diferencaEmHoras(final Date dataInicial, final Date dataFinal) {
        double result = 0.0;
        final long diferenca = dataFinal.getTime() - dataInicial.getTime();
        final long diferencaEmHoras = diferenca / 1000L / 60L / 60L;
        final long minutosRestantes = diferenca / 1000L / 60L % 60L;
        final double horasRestantes = minutosRestantes / 60.0;
        result = diferencaEmHoras + horasRestantes;
        return result;
    }
    
    public static double diferencaEmMinutos(final Date dataInicial, final Date dataFinal) {
        double result = 0.0;
        final long diferenca = dataFinal.getTime() - dataInicial.getTime();
        final double diferencaEmMinutos = (double)(diferenca / 1000L / 60L);
        final long segundosRestantes = diferenca / 1000L % 60L;
        result = diferencaEmMinutos + segundosRestantes / 60.0;
        return result;
    }
}
