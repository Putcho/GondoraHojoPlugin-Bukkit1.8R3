# GondoraHojoPlugin-Bukkit1.8R3
[GondoraMOD][gm]の補助プラグイン。
サーバーサイドでボートの二人乗りをできる限り実現させます。

##利用方法
まず、ウンディーネ（運転手）になりたい人が最初にボートに乗ります。
その後ボートの当たり判定（ウンディーネの足元付近）をクリックすることで、客席に座ることができます。
客席に座りたい場合は必ずウンディーネが乗ってる必要があります。

##仕様
###ボート本体
一度乗客が乗ったボートはプラグイン仕様のボート（ゴンドラ）に変化します。
このゴンドラは本来Bukkitには存在しないもののため、ワールドに保存することができず消えてしまいます。
その為、サーバーを停止した時もとい*本プラグインが無効になった時*にサーバー上にある*全てのゴンドラ*を削除されるようにしています。（通常のボートは削除されません）

また、*ボートに乗っている最中のウンディーネ*が*サーバーを退出したとき*も乗っていたゴンドラが削除されます。~~何故こんな仕様にしたのか思い出せない~~

###ラグについて
ボートの前に透明化したアーマースタンドをスポーンさせ、その上に乗客を乗せています。
その為、クライアント側の移動補完でどうしてもボートで移動してる際に乗客がボートから飛び出てしまいます。
停止して少し待つと乗客が正しい位置に戻りますので、*本プラグインはSSを撮る時などに利用することをお勧めします*。

##Download
[[1.8-R3]GondoraHojoPlugin.jar/Dropbox][download]

[gm]: http://forum.minecraftuser.jp/viewtopic.php?f=13&t=22468 "GondoraMOD"
[download]: https://www.dropbox.com/s/7abbt8vqhc9mqa4/%5B1.8-R3%5DGondoraHojoPlugin.jar?dl=0 "Dropbox"