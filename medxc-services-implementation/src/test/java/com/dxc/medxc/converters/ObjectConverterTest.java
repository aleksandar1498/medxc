/**
 *
 */
package com.dxc.medxc.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;


/**
 * @author amirchev
 */
class ObjectConverterTest {

    @Test
    @SuppressWarnings("nls")
    void  testConvert() {
        final FromObject from = new FromObject(2, "test_data", Timestamp.valueOf("2019-12-16 11:07:00"));
        final ToObject to = new ToObject(2.0, "test_data",
                ZonedDateTime.of(2019, 12, 16, 11, 7, 0, 0, ZoneId.of("UTC")));

        assertEquals(to, ObjectConverter.convert(r -> new ToObject(r.getTestInt(), r.getTestString(),
                r.getTestTimestamp().toLocalDateTime().atZone(ZoneId.of("UTC"))), from));
    }

    @Test
    @SuppressWarnings("nls")
    void testConvertList() {
        final List<FromObject> froms = new ArrayList<> () {
            /**
             *
             */
            private static final long serialVersionUID = -8977227369485346253L;

            {
                add(new FromObject(2, "test_data1", Timestamp.valueOf("2019-12-16 11:07:00")));
                add(new FromObject(3, "test_data2", Timestamp.valueOf("2019-12-16 12:27:00")));
                add(new FromObject(1, "test_data3", Timestamp.valueOf("2019-11-16 11:07:00")));
            }
        };

        final List<ToObject> tos = new ArrayList<> () {
            /**
             *
             */
            private static final long serialVersionUID = 4646352475100315994L;

            {
                add(new ToObject(2.0, "test_data1", ZonedDateTime.of(2019, 12, 16, 11, 7, 0, 0, ZoneId.of("UTC"))));
                add(new ToObject(3.0, "test_data2", ZonedDateTime.of(2019, 12, 16, 12, 27, 0, 0, ZoneId.of("UTC"))));
                add(new ToObject(1.0, "test_data3", ZonedDateTime.of(2019, 11, 16, 11, 7, 0, 0, ZoneId.of("UTC"))));
            }
        };

        assertEquals(tos, ObjectConverter.convertList(r -> new ToObject(r.getTestInt(), r.getTestString(),
                r.getTestTimestamp().toLocalDateTime().atZone(ZoneId.of("UTC"))), froms));

    }
    

    /**
    * Class only for testing - object to convert
    */
    private static class FromObject {
        private final int testInt;
        private final String testString;
        private final Timestamp testTimestamp;

        FromObject(final int testInt, final String testString, final Timestamp testTimestamp) {
            this.testInt = testInt;
            this.testString = testString;
            this.testTimestamp = testTimestamp;
        }

        protected int getTestInt() {
            return testInt;
        }

        protected String getTestString() {
            return testString;
        }

        protected Timestamp getTestTimestamp() {
            return testTimestamp;
        }
    }

    /**
     * Class only for testing - final object
     */
    private static class ToObject {
        private final double testDouble;
        private final String testString;
        private final ZonedDateTime testZonedDateTime;

        ToObject(final double testDouble, final String testString, final ZonedDateTime testZonedDateTime) {
            this.testDouble = testDouble;
            this.testString = testString;
            this.testZonedDateTime = testZonedDateTime;
        }

        protected double getTestDouble() {
            return testDouble;
        }

        protected String getTestString() {
            return testString;
        }

        protected ZonedDateTime getTestZonedDateTime() {
            return testZonedDateTime;
        }

        @Override
        public int hashCode() {
            return Objects.hash(Integer.valueOf((int) this.testDouble));
        }

        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (this.getClass() != obj.getClass()) {
                return false;
            }

            final ToObject other = (ToObject) obj;

            if (Double.compare(this.getTestDouble(), other.getTestDouble()) == 0 && this.getTestString().equals(other.getTestString())
                    && this.getTestZonedDateTime().equals(other.getTestZonedDateTime())) {
                return true;
            }
            return false;

        }
    }
}
