/*     */ package me.earth.phobos;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import me.earth.phobos.features.gui.custom.GuiCustomMainScreen;
/*     */ import me.earth.phobos.features.modules.misc.RPC;
/*     */ import me.earth.phobos.manager.ConfigManager;
/*     */ import me.earth.phobos.manager.CosmeticsManager;
/*     */ import me.earth.phobos.manager.FileManager;
/*     */ import me.earth.phobos.manager.HoleManager;
/*     */ import me.earth.phobos.manager.InventoryManager;
/*     */ import me.earth.phobos.manager.ModuleManager;
/*     */ import me.earth.phobos.manager.NoStopManager;
/*     */ import me.earth.phobos.manager.PacketManager;
/*     */ import me.earth.phobos.manager.PositionManager;
/*     */ import me.earth.phobos.manager.ReloadManager;
/*     */ import me.earth.phobos.manager.RotationManager;
/*     */ import me.earth.phobos.manager.SafetyManager;
/*     */ import me.earth.phobos.manager.ServerManager;
/*     */ import me.earth.phobos.manager.SpeedManager;
/*     */ import me.earth.phobos.manager.TextManager;
/*     */ import me.earth.phobos.manager.TimerManager;
/*     */ import me.earth.phobos.manager.TotemPopManager;
/*     */ import me.earth.phobos.manager.WaypointManager;
/*     */ import net.minecraft.util.Util;
/*     */ import net.minecraftforge.fml.common.Mod.EventHandler;
/*     */ 
/*     */ @Mod(modid = "acid", name = "Acid", version = "1.0.1")
/*     */ public class Phobos {
/*     */   public static final String MODID = "acid";
/*  31 */   public static final Logger LOGGER = LogManager.getLogger("Acid"); public static final String MODNAME = "Acid"; public static final String MODVER = "1.0.1";
/*     */   public static final String NAME_UNICODE = "ລႠ𝖎Ɒ 𝖏ʂ †ਮᙓ ϦƐﮑ†";
/*     */   public static final String PHOBOS_UNICODE = "ລႠ𝖎Ɒ";
/*     */   public static final String CHAT_SUFFIX = " | ລႠ𝖎Ɒ";
/*     */   public static final String PHOBOS_SUFFIX = " | ລႠ𝖎Ɒ ⸶․Ѻ․⸶";
/*     */   public static ModuleManager moduleManager;
/*     */   public static SpeedManager speedManager;
/*     */   public static PositionManager positionManager;
/*     */   public static RotationManager rotationManager;
/*     */   public static CommandManager commandManager;
/*     */   public static EventManager eventManager;
/*     */   public static ConfigManager configManager;
/*     */   public static FileManager fileManager;
/*     */   public static FriendManager friendManager;
/*     */   public static TextManager textManager;
/*     */   public static ColorManager colorManager;
/*     */   public static ServerManager serverManager;
/*     */   public static PotionManager potionManager;
/*     */   public static InventoryManager inventoryManager;
/*     */   public static TimerManager timerManager;
/*     */   public static PacketManager packetManager;
/*     */   public static ReloadManager reloadManager;
/*     */   public static TotemPopManager totemPopManager;
/*     */   public static HoleManager holeManager;
/*     */   public static NotificationManager notificationManager;
/*     */   public static SafetyManager safetyManager;
/*     */   public static GuiCustomMainScreen customMainScreen;
/*     */   public static CosmeticsManager cosmeticsManager;
/*     */   public static NoStopManager baritoneManager;
/*     */   public static WaypointManager waypointManager;
/*     */   @Instance
/*     */   public static Phobos INSTANCE;
/*     */   private static boolean unloaded = false;
/*     */   
/*     */   public static void load() {
/*  66 */     LOGGER.info("\n\nLoading Acid 1.0.1");
/*  67 */     unloaded = false;
/*  68 */     if (reloadManager != null) {
/*  69 */       reloadManager.unload();
/*  70 */       reloadManager = null;
/*     */     } 
/*  72 */     baritoneManager = new NoStopManager();
/*  73 */     totemPopManager = new TotemPopManager();
/*  74 */     timerManager = new TimerManager();
/*  75 */     packetManager = new PacketManager();
/*  76 */     serverManager = new ServerManager();
/*  77 */     colorManager = new ColorManager();
/*  78 */     textManager = new TextManager();
/*  79 */     moduleManager = new ModuleManager();
/*  80 */     speedManager = new SpeedManager();
/*  81 */     rotationManager = new RotationManager();
/*  82 */     positionManager = new PositionManager();
/*  83 */     commandManager = new CommandManager();
/*  84 */     eventManager = new EventManager();
/*  85 */     configManager = new ConfigManager();
/*  86 */     fileManager = new FileManager();
/*  87 */     friendManager = new FriendManager();
/*  88 */     potionManager = new PotionManager();
/*  89 */     inventoryManager = new InventoryManager();
/*  90 */     holeManager = new HoleManager();
/*  91 */     notificationManager = new NotificationManager();
/*  92 */     safetyManager = new SafetyManager();
/*  93 */     waypointManager = new WaypointManager();
/*  94 */     LOGGER.info("Initialized Managers");
/*  95 */     moduleManager.init();
/*  96 */     LOGGER.info("Modules loaded.");
/*  97 */     configManager.init();
/*  98 */     eventManager.init();
/*  99 */     LOGGER.info("EventManager loaded.");
/* 100 */     textManager.init(true);
/* 101 */     moduleManager.onLoad();
/* 102 */     totemPopManager.init();
/* 103 */     timerManager.init();
/* 104 */     if (((RPC)moduleManager.getModuleByClass(RPC.class)).isEnabled()) {
/* 105 */       DiscordPresence.start();
/*     */     }
/* 107 */     cosmeticsManager = new CosmeticsManager();
/* 108 */     LOGGER.info("Acid initialized!\n");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getVersion() {
/* 113 */     return getVersion();
/*     */   }
/*     */   
/*     */   public static void unload(boolean unload) {
/* 117 */     LOGGER.info("\n\nUnloading Acid 1.0.1");
/* 118 */     if (unload) {
/* 119 */       reloadManager = new ReloadManager();
/* 120 */       reloadManager.init((commandManager != null) ? commandManager.getPrefix() : ".");
/*     */     } 
/* 122 */     if (baritoneManager != null) {
/* 123 */       baritoneManager.stop();
/*     */     }
/* 125 */     onUnload();
/* 126 */     eventManager = null;
/* 127 */     holeManager = null;
/* 128 */     timerManager = null;
/* 129 */     moduleManager = null;
/* 130 */     totemPopManager = null;
/* 131 */     serverManager = null;
/* 132 */     colorManager = null;
/* 133 */     textManager = null;
/* 134 */     speedManager = null;
/* 135 */     rotationManager = null;
/* 136 */     positionManager = null;
/* 137 */     commandManager = null;
/* 138 */     configManager = null;
/* 139 */     fileManager = null;
/* 140 */     friendManager = null;
/* 141 */     potionManager = null;
/* 142 */     inventoryManager = null;
/* 143 */     notificationManager = null;
/* 144 */     safetyManager = null;
/* 145 */     LOGGER.info("Acid unloaded!\n");
/*     */   }
/*     */   
/*     */   public static void reload() {
/* 149 */     unload(false);
/* 150 */     load();
/*     */   }
/*     */   
/*     */   public static void onUnload() {
/* 154 */     if (!unloaded) {
/*     */       try {
/* 156 */         IRC.INSTANCE.disconnect();
/* 157 */       } catch (IOException e) {
/* 158 */         e.printStackTrace();
/*     */       } 
/* 160 */       eventManager.onUnload();
/* 161 */       moduleManager.onUnload();
/* 162 */       configManager.saveConfig(configManager.config.replaceFirst("acid/", ""));
/* 163 */       moduleManager.onUnloadPost();
/* 164 */       timerManager.unload();
/* 165 */       unloaded = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setWindowIcon() {
/* 171 */     if (Util.func_110647_a() != Util.EnumOS.OSX) {
/* 172 */       try(InputStream inputStream16x = Minecraft.class.getResourceAsStream("/assets/acid/icons/acid-16x.png"); 
/* 173 */           InputStream inputStream32x = Minecraft.class.getResourceAsStream("/assets/acid/icons/acid-32x.png")) {
/* 174 */         ByteBuffer[] icons = { IconUtils.INSTANCE.readImageToBuffer(inputStream16x), IconUtils.INSTANCE.readImageToBuffer(inputStream32x) };
/* 175 */         Display.setIcon(icons);
/* 176 */       } catch (Exception e) {
/* 177 */         LOGGER.error("Couldn't set Windows Icon", e);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void preInit(FMLPreInitializationEvent event) {
/* 184 */     LOGGER.info("balls in yo jaws");
/* 185 */     LOGGER.info("shitter above");
/* 186 */     LOGGER.info("suck this d noob");
/* 187 */     LOGGER.info("acid on top");
/* 188 */     LOGGER.info("deez nuts");
/*     */   }
/*     */ 
/*     */   
/*     */   private void setWindowsIcon() {
/* 193 */     setWindowIcon();
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void init(FMLInitializationEvent event) {
/* 198 */     HWIDManager.hwidCheck();
/* 199 */     setWindowsIcon();
/* 200 */     customMainScreen = new GuiCustomMainScreen();
/* 201 */     Display.setTitle("Acid | 1.0.1 fobus edition");
/* 202 */     load();
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\Phobos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */