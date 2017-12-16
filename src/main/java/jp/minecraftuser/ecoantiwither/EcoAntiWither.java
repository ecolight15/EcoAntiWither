
package jp.minecraftuser.ecoantiwither;

import jp.minecraftuser.ecoantiwither.command.EcoAntiWitherCommand;
import jp.minecraftuser.ecoframework.CommandFrame;
import jp.minecraftuser.ecoframework.PluginFrame;
import jp.minecraftuser.ecoantiwither.command.EcoAntiWitherReloadCommand;
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
    }

    /**
     * 終了時処理
     */
    @Override
    public void onDisable() {
        disable();
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
        CommandFrame cmd = new EcoAntiWitherCommand(this, "wither");
        cmd.addCommand(new EcoAntiWitherReloadCommand(this, "reload"));
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
