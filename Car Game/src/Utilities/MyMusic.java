package Utilities;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * מחלקה זו מאפשרת לנגן קובצי מוזיקה מסוג WAV ולשלוט ברמת השמע.
 */
public class MyMusic {
    private static Map<String, Clip> clipMap = new HashMap<>();

    /**
     * מתודה זו מקבלת מיקום של קובץ מוזיקה בפורמט WAV ומנגנת אותו בלולאה.
     *
     * @param id זיהוי ייחודי לסאונד.
     * @param location המיקום של קובץ המוזיקה (נתיב מלא לקובץ).
     * @param volume רמת השמע (0.0 עד 1.0).
     * @param loop האם לנגן בלולאה או לא.
     */
    public static void playMusic(String id, String location, float volume, boolean loop) {
        try {
            File musicFile = new File(location);
            if (musicFile.exists()) {
                try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile)) {
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);

                    // קביעת רמת השמע
                    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    float range = gainControl.getMaximum() - gainControl.getMinimum();
                    float dB = gainControl.getMinimum() + (range * volume);
                    gainControl.setValue(dB);

                    if (loop) {
                        clip.loop(Clip.LOOP_CONTINUOUSLY); // ניגון בלולאה
                    } else {
                        clip.loop(0); // ביצוע ניגון חד פעמי
                    }

                    clipMap.put(id, clip);
                }
            } else {
                System.out.println("Cannot find the file");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * מתודה זו מפסיקה את הנגינה של קובץ מוזיקה מסוים.
     *
     * @param id זיהוי ייחודי לסאונד.
     */
    public static void stopMusic(String id) {
        Clip clip = clipMap.get(id);
        if (clip != null) {
            clip.stop();
            clip.close();
            clipMap.remove(id);
        } else {
            System.out.println("Sound with id " + id + " not found");
        }
    }

    /**
     * מתודה זו עוצרת את הנגינה של כל קובצי המוזיקה.
     */
    public static void stopAllMusic() {
        for (Clip clip : clipMap.values()) {
            if (clip.isRunning()) {
                clip.stop();
                clip.close();
            }
        }
        clipMap.clear();
    }
}