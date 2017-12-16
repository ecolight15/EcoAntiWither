
package jp.minecraftuser.ecoantiwither.listener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import jp.minecraftuser.ecoframework.PluginFrame;
import jp.minecraftuser.ecoframework.ListenerFrame;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * 敵MODイベント処理リスナークラス
 * @author ecolight
 */
public class CreatureListener extends ListenerFrame{

    /**
     * コンストラクタ
     * @param plg_ プラグインインスタンス
     * @param name_ 名前
     */
    public CreatureListener(PluginFrame plg_, String name_) {
        super(plg_, name_);
    }

    /**
     * 敵MOBスポーンイベント処理
     * @param event イベント情報
     */
    @EventHandler(priority=EventPriority.LOWEST)
    public void CreatureSpawn(CreatureSpawnEvent event)
    {
        LivingEntity ent = event.getEntity();
        if ((ent.getType() == EntityType.WITHER) && 
            (!(conf.getArrayList("DisableWorlds").contains(ent.getWorld().getName())))) {
            StringBuilder sb = new StringBuilder(ChatColor.YELLOW.toString());
            sb.append("[");
            sb.append(plg.getName());
            sb.append("] ウィザーの召喚をキャンセルしました。");
            Location loc = ent.getLocation();
            sb.append(loc.toString());
            for (Player pl: plg.getServer().getOnlinePlayers()) {
                if ((loc.getWorld().getName().equalsIgnoreCase(pl.getWorld().getName())) &&
                    (loc.distance(pl.getLocation()) <= 5)) {
                    sb.append(" 至近プレイヤー[");
                    sb.append(pl.getName());
                    sb.append("]");
                }
            }
            // 外部ファイルログ
            try {
                SimpleDateFormat sd = new SimpleDateFormat("[yyyy/MM/dd HH:mm:ss.SSS]");
                File file = new File(plg.getDataFolder().getAbsolutePath()+"/wither_log.txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
                String token_message = sd.format(new Date()) + sb.toString();
                bw.write(token_message);
                bw.newLine();
                bw.close();
            } catch (Exception ex) {
                log.warning("ファイル書き込みエラー検知");
                log.warning(ex.getLocalizedMessage());
            }

            plg.getServer().broadcastMessage(sb.toString());
            event.setCancelled(true);
        }
    }
}
