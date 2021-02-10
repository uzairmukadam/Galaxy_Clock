package com.uzitech.galaxyclock.Support;

public class Events {

    public static class dateInfo {
        private final String date;

        public dateInfo(String date) {
            this.date = date;
        }

        public String getMessage() {
            return date;
        }
    }

    public static class modeInfo {
        private final int mode;

        public modeInfo(int mode) {
            this.mode = mode;
        }

        public int getMessage() {
            return mode;
        }
    }

    public static class clockInfo {
        private final int clock;

        public clockInfo(int clock) {
            this.clock = clock;
        }

        public int getMessage() {
            return clock;
        }
    }
}
