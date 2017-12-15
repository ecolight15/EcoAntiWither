
package jp.minecraftuser.ecoantiwither;

import jp.minecraftuser.ecoframework.CommandFrame;
import jp.minecraftuser.ecoframework.PluginFrame;
import jp.minecraftuser.ecoantiwither.command.ReloadCommand;
import jp.minecraftuser.ecoantiwither.config.EcoAntiWitherConfig;
import jp.minecraftuser.ecoantiwither.listener.CreatureListener;

/**
 * プラグインメインクラス
 * @author ecolight
 */
public class EcoAntiWither extends PluginFrame{

    /**
     * 起動時処理
     */
    @Override
    public void onEnable() {
        initialize();
        getLogger().info(getName()+" Enable");
    }

    /**
     * 終了時処理
     */
    @Override
    public void onDisable() {
        disable();
        getLogger().info(getName()+" Disable");
    }

    /**
     * 設定初期化
     */
    @Override
    public void initializeConfig() {
        EcoAntiWitherConfig conf = new EcoAntiWitherConfig(this);
        conf.registerArrayString("DisableWorlds");
        registerPluginConfig(conf);
    }

    /**
     * コマンド初期化
     */
    @Override
    public void initializeCommand() {
        CommandFrame cmd = new ReloadCommand(this, "wither");
        cmd.addCommand(new ReloadCommand(this, "reload"));
        registerPluginCommand(cmd);
    }

    /**
     * イベントリスナー初期化
     */
    @Override
    public void initializeListener() {
        registerPluginListener(new CreatureListener(this, "creature"));
    }
}
