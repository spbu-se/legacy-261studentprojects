/*
 * Tests for Cloud allpication by Korshakov Stepan
 * 261 Group - 2009
 */

import hotheart.clouds.BabyCarrierType;
import hotheart.clouds.Cloud;
import hotheart.clouds.CreatureType;
import hotheart.clouds.DayLightType;
import hotheart.clouds.BabyCarrier;
import hotheart.clouds.CreatureTable;
import hotheart.clouds.IDayLight;
import hotheart.clouds.ILuminary;
import hotheart.clouds.IMagic;
import hotheart.clouds.IWeather;
import hotheart.clouds.IWind;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Korshakov Stepan
 */
public class CloudTest {

    Mockery context;

    private void testCloud(final boolean isLum, final int wind,
            final DayLightType daylight, final CreatureType type,
            final BabyCarrierType carierType) {

        context = new Mockery();
        final IWeather mockWeather = context.mock(IWeather.class);
        final ILuminary mockLum = context.mock(ILuminary.class);
        final IWind mockWind = context.mock(IWind.class);
        final IDayLight mockDayLight = context.mock(IDayLight.class);

        final IMagic mockMagic = context.mock(IMagic.class);

        final BabyCarrier mockCarier = new BabyCarrier(carierType);

        // Carrier

        if (carierType == BabyCarrierType.Daemon) {
            context.checking(new Expectations() {

                {
                    oneOf(mockMagic).CallDaemon();
                    will(returnValue(mockCarier));
                    never(mockMagic).CallStork();
                    will(returnValue(null));
                }
            });
        } else {
            context.checking(new Expectations() {

                {
                    oneOf(mockMagic).CallStork();
                    will(returnValue(mockCarier));
                    never(mockMagic).CallDaemon();
                    will(returnValue(null));
                }
            });
        }

        // Weather

        context.checking(new Expectations() {

            {
                allowing(mockLum).isShining();
                will(returnValue(isLum));
                allowing(mockWind).getSpeed();
                will(returnValue(wind));
                allowing(mockDayLight).getDayLightType();
                will(returnValue(daylight));
            }
        });


        context.checking(new Expectations() {

            {
                allowing(mockWeather).getDayLight();
                will(returnValue(mockDayLight));
                allowing(mockWeather).getLuminary();
                will(returnValue(mockLum));
                allowing(mockWeather).getWind();
                will(returnValue(mockWind));
            }
        });




        Cloud cloud = new Cloud(mockWeather, mockMagic);
        BabyCarrier carrier = cloud.getCreature();

        context.assertIsSatisfied();

        assertEquals(carrier.getType(), carierType);

        assertEquals(carrier.getCreature().getCreatureType(), type);

    }

    @Test
    public void TestCloud() {
        for (CreatureTable.CreatureTableRow row : CreatureTable.Table) {
            for (int i = row.windStart; i <= row.windEnd; i++) {
                testCloud(row.isShinig, i, row.daylight, row.creature, row.carrier);
            }
        }
    }
}