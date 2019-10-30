package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }
    
    @Test
    public void lisataanLiikaaTavaraa() {
        varasto.lisaaVarastoon(13);
        double saldonMaara = 10;
        assertEquals(0, saldonMaara, varasto.getSaldo());
    }
    
    @Test
    public void otetaanLiikaaTavaraa() {
        varasto.lisaaVarastoon(5);
        double saatuMaara = varasto.otaVarastosta(13);
        assertEquals(0, 0, varasto.getSaldo());
        assertEquals(0, 5, saatuMaara);
    }
    
    @Test
    public void otetaanNegatiivinen() {
        double saatuMaara = varasto.otaVarastosta(-90);
        assertEquals(0, saatuMaara, 0);
    }
    
    @Test
    public void lisataanNegatiivinen() {
        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(-6);
        assertEquals(0, 5, varasto.getSaldo());
    }
    
    @Test
    public void tehdaanNegatiivinenTilavuus() {
        Varasto varasto2 = new Varasto(-8);
        assertEquals(0, varasto2.getTilavuus(), 0);
    }
    
    @Test
    public void tehdaanNegatiivinenTilavuusSaldolla() {
        Varasto varasto2 = new Varasto(-8, 10);
        assertEquals(0, varasto2.getTilavuus(), 0);
    }
    
    @Test
    public void tehdaanNegatiivinenSaldo() {
        Varasto varasto2 = new Varasto(10, -10);
        assertEquals(0, varasto2.getSaldo(), 0);
    }
    
    @Test
    public void tehdaanLiianSuuriSaldo() {
        Varasto varasto2 = new Varasto(10, 11);
        assertEquals(0, varasto2.getSaldo(), 10);
    }
    
    @Test
    public void tehdaanNormaaliVarasto() {
        Varasto varasto2 = new Varasto(14, 10);
        assertEquals(0, varasto2.getSaldo(), 10);
        assertEquals(0, varasto2.getTilavuus(), 14);
    }
    
    @Test
    public void merkkijonoToimii() {
        varasto.lisaaVarastoon(3);
        assertEquals("saldo = 3.0, vielä tilaa 7.0", varasto.toString());
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}