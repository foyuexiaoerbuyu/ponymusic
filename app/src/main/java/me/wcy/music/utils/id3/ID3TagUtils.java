package me.wcy.music.utils.id3;

import android.util.Log;

import org.blinkenlights.jid3.ID3Exception;
import org.blinkenlights.jid3.MP3File;
import org.blinkenlights.jid3.MediaFile;
import org.blinkenlights.jid3.v2.ID3V2_3_0Tag;

import java.io.File;

import me.wcy.music.utils.LogUtils;

/**
 * ID3 Tag 工具类<br>
 * 基于<a href="https://blinkenlights.org/jid3/">JID3</a>编写<br>
 * Created by hzwangchenyan on 2017/8/11.
 */
public class ID3TagUtils {
    private static final String TAG = "ID3TagUtils";

    /**
     * @param clearOriginal 是否清除原始标签
     */
    public static boolean setID3Tags(File sourceFile, ID3Tags id3Tags, boolean clearOriginal) {
        if (sourceFile == null || !sourceFile.exists()) {
            LogUtils.e(TAG, "source file is illegal");
            return false;
        }

        if (id3Tags == null) {
            LogUtils.e(TAG, "id3 tags is illegal");
            return false;
        }

        MediaFile oMediaFile = new MP3File(sourceFile);
        ID3V2_3_0Tag oID3V2_3_0Tag = null;
        if (clearOriginal) {
            oID3V2_3_0Tag = new ID3V2_3_0Tag();
        } else {
            try {
                oID3V2_3_0Tag = (ID3V2_3_0Tag) oMediaFile.getID3V2Tag();
            } catch (ID3Exception e) {
                e.printStackTrace();
            }
            if (oID3V2_3_0Tag == null) {
                oID3V2_3_0Tag = new ID3V2_3_0Tag();
            }
        }

        try {
            id3Tags.fillID3Tag(oID3V2_3_0Tag);
            oMediaFile.setID3Tag(oID3V2_3_0Tag);
            oMediaFile.sync();
            return true;
        } catch (ID3Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
