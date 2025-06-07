# EcoAntiWither

**EcoAntiWither** は、Minecraft サーバーで特定のワールドにおけるウィザーのスポーンを防ぐ Spigot プラグインです。

## 機能

- 設定可能なワールド名のマッチングルールに基づいてウィザーのスポーンをキャンセル
- ウィザーのスポーンがキャンセルされた際のリアルタイム通知
- 詳細なログファイル出力（タイムスタンプ付き）
- 近くにいるプレイヤーの情報も含む包括的なログ記録

## 要件

- Minecraft サーバー（Spigot/Paper）1.18.2 以上
- Java 8 以上
- EcoFramework 0.28 以上

## インストール

1. [EcoFramework](https://github.com/ecolight15/EcoFramework) をサーバーの `plugins` フォルダーにダウンロード・配置
2. EcoAntiWither.jar をサーバーの `plugins` フォルダーに配置
3. サーバーを再起動
4. 生成された設定ファイルを編集（オプション）

## 設定

プラグインフォルダー内の `config.yml` ファイルで設定を行います：

```yaml
# MatchingType : StartsWith, equals, equalsIgnoreCase
MatchingType: StartsWith
DisableWorlds:
  - world1
  - world2
```

### 設定項目

| 項目 | 説明 | 設定値 |
|------|------|--------|
| `MatchingType` | ワールド名のマッチング方式 | `StartsWith`, `equals`, `equalsIgnoreCase` |
| `DisableWorlds` | ウィザーのスポーンを禁止するワールド名のリスト | 文字列配列 |

### マッチングタイプの詳細

- **StartsWith**: ワールド名が指定された文字列で始まる場合にマッチ
- **equals**: ワールド名が指定された文字列と完全に一致する場合にマッチ
- **equalsIgnoreCase**: 大文字小文字を無視してワールド名が指定された文字列と一致する場合にマッチ

## コマンド

| コマンド | 説明 | 権限 |
|----------|------|------|
| `/wither` | 基本コマンド（パラメータ不足メッセージを表示） | `ecoantiwither` |
| `/wither reload` | 設定ファイルを再読み込み | `ecoantiwither.reload` |

## 権限

| 権限 | 説明 | デフォルト |
|------|------|------------|
| `ecoantiwither` | 基本コマンドの使用権限 | OP |
| `ecoantiwither.reload` | リロードコマンドの使用権限 | OP |

## 使用例

### 例1: 特定のワールドでウィザーを完全に禁止

```yaml
MatchingType: equals
DisableWorlds:
  - world_nether
  - creative_world
```

### 例2: 特定の接頭辞を持つワールドでウィザーを禁止

```yaml
MatchingType: StartsWith
DisableWorlds:
  - survival_
  - mini_
```

### 例3: 大文字小文字を無視した制限

```yaml
MatchingType: equalsIgnoreCase
DisableWorlds:
  - SPAWN
  - Hub
```

## ログ機能

ウィザーのスポーンがキャンセルされると、以下の情報が記録されます：

- **サーバーログ**: コンソールに出力
- **プレイヤー通知**: サーバー内の全プレイヤーに通知
- **ファイルログ**: `plugins/EcoAntiWither/wither_log.txt` に詳細ログを保存

ログには以下の情報が含まれます：
- タイムスタンプ
- ウィザーがスポーンしようとした座標
- 近くにいたプレイヤー（5ブロック以内）の名前

## 開発情報

- **作者**: ecolight
- **バージョン**: 0.6
- **ライセンス**: GNU Lesser General Public License v3.0
- **依存関係**: EcoFramework

## ライセンス

このプロジェクトは [GNU Lesser General Public License v3.0](LICENSE) の下でライセンスされています。

## サポート

バグレポートや機能リクエストは、[GitHub Issues](https://github.com/ecolight15/EcoAntiWither/issues) でお願いします。