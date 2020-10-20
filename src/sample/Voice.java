package sample;

import com.sun.speech.freetts.VoiceManager;

public class Voice {
    public static void speech (String text) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager voiceManager = VoiceManager.getInstance();
        com.sun.speech.freetts.Voice sintheticVoice = voiceManager.getVoice("kevin16");
        sintheticVoice.allocate();
        sintheticVoice.speak(text);
        sintheticVoice.deallocate();
    }
}
