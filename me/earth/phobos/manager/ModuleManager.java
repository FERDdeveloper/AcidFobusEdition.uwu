/*     */ package me.earth.phobos.manager;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import me.earth.phobos.event.events.Render2DEvent;
/*     */ import me.earth.phobos.event.events.Render3DEvent;
/*     */ import me.earth.phobos.features.Feature;
/*     */ import me.earth.phobos.features.modules.Module;
/*     */ import me.earth.phobos.features.modules.client.Media;
/*     */ import me.earth.phobos.features.modules.client.ServerModule;
/*     */ import me.earth.phobos.features.modules.combat.ArmorMessage;
/*     */ import me.earth.phobos.features.modules.combat.Killaura;
/*     */ import me.earth.phobos.features.modules.combat.Offhand;
/*     */ import me.earth.phobos.features.modules.misc.AutoGG;
/*     */ import me.earth.phobos.features.modules.misc.BuildHeight;
/*     */ import me.earth.phobos.features.modules.misc.Companion;
/*     */ import me.earth.phobos.features.modules.misc.Exploits;
/*     */ import me.earth.phobos.features.modules.misc.MobOwner;
/*     */ import me.earth.phobos.features.modules.misc.NoSoundLag;
/*     */ import me.earth.phobos.features.modules.movement.SafeWalk;
/*     */ import me.earth.phobos.features.modules.player.Replenish;
/*     */ import me.earth.phobos.features.modules.player.TimerSpeed;
/*     */ import me.earth.phobos.features.modules.render.XRay;
/*     */ 
/*     */ public class ModuleManager extends Feature {
/*  27 */   public ArrayList<Module> modules = new ArrayList<>();
/*  28 */   public List<Module> sortedModules = new ArrayList<>();
/*  29 */   public List<Module> alphabeticallySortedModules = new ArrayList<>();
/*  30 */   public Map<Module, Color> moduleColorMap = new HashMap<>();
/*     */   
/*     */   public void init() {
/*  33 */     this.modules.add(new PopChams());
/*  34 */     this.modules.add(new Offhand());
/*  35 */     this.modules.add(new Surround());
/*  36 */     this.modules.add(new AutoTrap());
/*  37 */     this.modules.add(new AutoCrystal());
/*  38 */     this.modules.add(new Criticals());
/*  39 */     this.modules.add(new BowSpam());
/*  40 */     this.modules.add(new Killaura());
/*  41 */     this.modules.add(new HoleFiller());
/*  42 */     this.modules.add(new Selftrap());
/*  43 */     this.modules.add(new Webaura());
/*  44 */     this.modules.add(new AutoArmor());
/*  45 */     this.modules.add(new AntiTrap());
/*  46 */     this.modules.add(new BedBomb());
/*  47 */     this.modules.add(new ArmorMessage());
/*  48 */     this.modules.add(new Crasher());
/*  49 */     this.modules.add(new Auto32k());
/*  50 */     this.modules.add(new AntiCrystal());
/*  51 */     this.modules.add(new AnvilAura());
/*  52 */     this.modules.add(new GodModule());
/*  53 */     this.modules.add(new NoteBot());
/*  54 */     this.modules.add(new ChatModifier());
/*  55 */     this.modules.add(new BetterPortals());
/*  56 */     this.modules.add(new BuildHeight());
/*  57 */     this.modules.add(new NoHandShake());
/*  58 */     this.modules.add(new AutoRespawn());
/*  59 */     this.modules.add(new NoRotate());
/*  60 */     this.modules.add(new MCF());
/*  61 */     this.modules.add(new PingSpoof());
/*  62 */     this.modules.add(new NoSoundLag());
/*  63 */     this.modules.add(new AutoLog());
/*  64 */     this.modules.add(new KitDelete());
/*  65 */     this.modules.add(new Exploits());
/*  66 */     this.modules.add(new Spammer());
/*  67 */     this.modules.add(new AntiVanish());
/*  68 */     this.modules.add(new ExtraTab());
/*  69 */     this.modules.add(new MobOwner());
/*  70 */     this.modules.add(new Nuker());
/*  71 */     this.modules.add(new AutoReconnect());
/*  72 */     this.modules.add(new NoAFK());
/*  73 */     this.modules.add(new Tracker());
/*  74 */     this.modules.add(new PackReload());
/*  75 */     this.modules.add(new Logger());
/*  76 */     this.modules.add(new RPC());
/*  77 */     this.modules.add(new AutoGG());
/*  78 */     this.modules.add(new Godmode());
/*  79 */     this.modules.add(new Companion());
/*  80 */     this.modules.add(new EntityControl());
/*  81 */     this.modules.add(new ReverseStep());
/*  82 */     this.modules.add(new Bypass());
/*  83 */     this.modules.add(new Strafe());
/*  84 */     this.modules.add(new Velocity());
/*  85 */     this.modules.add(new Speed());
/*  86 */     this.modules.add(new Step());
/*  87 */     this.modules.add(new StepOld());
/*  88 */     this.modules.add(new Sprint());
/*  89 */     this.modules.add(new AntiLevitate());
/*  90 */     this.modules.add(new Phase());
/*  91 */     this.modules.add(new Static());
/*  92 */     this.modules.add(new TPSpeed());
/*  93 */     this.modules.add(new Flight());
/*  94 */     this.modules.add(new ElytraFlight());
/*  95 */     this.modules.add(new NoSlowDown());
/*  96 */     this.modules.add(new HoleTP());
/*  97 */     this.modules.add(new NoFall());
/*  98 */     this.modules.add(new IceSpeed());
/*  99 */     this.modules.add(new AutoWalk());
/* 100 */     this.modules.add(new TestPhase());
/* 101 */     this.modules.add(new LongJump());
/* 102 */     this.modules.add(new LagBlock());
/* 103 */     this.modules.add(new FastSwim());
/* 104 */     this.modules.add(new StairSpeed());
/* 105 */     this.modules.add(new BoatFly());
/* 106 */     this.modules.add(new VanillaSpeed());
/* 107 */     this.modules.add(new Reach());
/* 108 */     this.modules.add(new LiquidInteract());
/* 109 */     this.modules.add(new FakePlayer());
/* 110 */     this.modules.add(new TimerSpeed());
/* 111 */     this.modules.add(new FastPlace());
/* 112 */     this.modules.add(new Freecam());
/* 113 */     this.modules.add(new Speedmine());
/* 114 */     this.modules.add(new SafeWalk());
/* 115 */     this.modules.add(new Blink());
/* 116 */     this.modules.add(new MultiTask());
/* 117 */     this.modules.add(new BlockTweaks());
/* 118 */     this.modules.add(new XCarry());
/* 119 */     this.modules.add(new Replenish());
/* 120 */     this.modules.add(new NoHunger());
/* 121 */     this.modules.add(new Jesus());
/* 122 */     this.modules.add(new Scaffold());
/* 123 */     this.modules.add(new EchestBP());
/* 124 */     this.modules.add(new TpsSync());
/* 125 */     this.modules.add(new MCP());
/* 126 */     this.modules.add(new Burrow());
/* 127 */     this.modules.add(new TrueDurability());
/* 128 */     this.modules.add(new Yaw());
/* 129 */     this.modules.add(new NoDDoS());
/* 130 */     this.modules.add(new StorageESP());
/* 131 */     this.modules.add(new NoRender());
/* 132 */     this.modules.add(new SmallShield());
/* 133 */     this.modules.add(new Fullbright());
/* 134 */     this.modules.add(new CameraClip());
/* 135 */     this.modules.add(new Chams());
/* 136 */     this.modules.add(new Skeleton());
/* 137 */     this.modules.add(new ESP());
/* 138 */     this.modules.add(new HoleESP());
/* 139 */     this.modules.add(new BlockHighlight());
/* 140 */     this.modules.add(new Trajectories());
/* 141 */     this.modules.add(new Tracer());
/* 142 */     this.modules.add(new LogoutSpots());
/* 143 */     this.modules.add(new XRay());
/* 144 */     this.modules.add(new PortalESP());
/* 145 */     this.modules.add(new Ranges());
/* 146 */     this.modules.add(new OffscreenESP());
/* 147 */     this.modules.add(new HandColor());
/* 148 */     this.modules.add(new VoidESP());
/* 149 */     this.modules.add(new Cosmetics());
/* 150 */     this.modules.add(new TestNametags());
/* 151 */     this.modules.add(new CrystalScale());
/* 152 */     this.modules.add(new Notifications());
/* 153 */     this.modules.add(new HUD());
/* 154 */     this.modules.add(new ToolTips());
/* 155 */     this.modules.add(new FontMod());
/* 156 */     this.modules.add(new ClickGui());
/* 157 */     this.modules.add(new Managers());
/* 158 */     this.modules.add(new Components());
/* 159 */     this.modules.add(new StreamerMode());
/* 160 */     this.modules.add(new Capes());
/* 161 */     this.modules.add(new Colors());
/* 162 */     this.modules.add(new ServerModule());
/* 163 */     this.modules.add(new Screens());
/* 164 */     this.modules.add(new Media());
/* 165 */     this.modules.add(new IRC());
/* 166 */     this.modules.add(new OffhandGqrlEdition());
/* 167 */     this.modules.add(new SilentXp());
/* 168 */     this.modules.add(new Display());
/* 169 */     this.modules.add(new ViewModel());
/*     */     
/* 171 */     this.modules.add(new FriendSettings());
/* 172 */     this.modules.add(new PacketMend());
/* 173 */     this.modules.add(new FakeKick());
/* 174 */     this.modules.add(new AutoCrystalCustom());
/* 175 */     this.modules.add(new OldFagDupe());
/* 176 */     this.modules.add(new NoFallOldfag());
/* 177 */     this.modules.add(new Logo());
/* 178 */     this.modules.add(new ShulkerAura());
/* 179 */     this.modules.add(new Anchor());
/* 180 */     this.modules.add(new AntiVoid());
/* 181 */     this.modules.add(new AntiWeb());
/* 182 */     this.modules.add(new EntityNotifier());
/* 183 */     this.modules.add(new AutoSignKick());
/*     */     
/* 185 */     this.modules.add(new StashLogger());
/* 186 */     this.modules.add(new AntiKick());
/* 187 */     this.modules.add(new PosDesync());
/* 188 */     this.modules.add(new HasteSpoof());
/* 189 */     this.modules.add(new LastPosTP());
/* 190 */     this.modules.add(new MonkeBook());
/* 191 */     this.modules.add(new ThisDiXP());
/* 192 */     this.modules.add(new GhastFinder());
/* 193 */     this.modules.add(new Quiver());
/* 194 */     this.modules.add(new ThisDiXPSilent());
/* 195 */     this.modules.add(new PacketCanceller());
/*     */     
/* 197 */     this.modules.add(new PhysicsCapes());
/* 198 */     this.modules.add(new ShoulderEntity());
/* 199 */     this.modules.add(new CCPacketFly());
/* 200 */     this.modules.add(new Animations());
/*     */     
/* 202 */     this.modules.add(new AutoBuilder());
/* 203 */     this.modules.add(new ChorusESP());
/* 204 */     this.modules.add(new SkyColor());
/* 205 */     this.modules.add(new BlockPhase());
/*     */     
/* 207 */     this.modules.add(new NoSlowBypass());
/* 208 */     this.modules.add(new CloseMC());
/* 209 */     this.modules.add(new ItemPhysics());
/* 210 */     this.modules.add(new AutoRAT());
/* 211 */     this.modules.add(new OffhandSwing());
/* 212 */     this.modules.add(new Nametags());
/* 213 */     this.modules.add(new BowMcBomb());
/* 214 */     this.modules.add(new AutoRonaldo());
/* 215 */     this.modules.add(new KambingPopChams());
/* 216 */     this.modules.add(new Aspect());
/* 217 */     this.modules.add(new EntityHunger());
/* 218 */     this.modules.add(new PortalBuilder());
/* 219 */     this.modules.add(new AutoSuicide());
/* 220 */     this.modules.add(new PacketSwing());
/* 221 */     this.modules.add(new BlockVision());
/* 222 */     this.modules.add(new GUIBlur());
/* 223 */     this.modules.add(new HitMarkers());
/* 224 */     this.modules.add(new Wireframe());
/* 225 */     this.modules.add(new GlintModify());
/* 226 */     this.modules.add(new Drunk());
/* 227 */     this.modules.add(new BoatPlace());
/* 228 */     this.modules.add(new AirJump());
/* 229 */     this.modules.add(new Parkour());
/* 230 */     this.modules.add(new SilentChorus());
/* 231 */     this.modules.add(new EntityVanish());
/* 232 */     this.modules.add(new ChestStealer());
/* 233 */     this.modules.add(new AutoKickBow());
/* 234 */     this.modules.add(new Zoom());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 260 */     this.moduleColorMap.put((Module)getModuleByClass(PopChams.class), new Color(22, 213, 196));
/* 261 */     this.moduleColorMap.put((Module)getModuleByClass(AntiTrap.class), new Color(128, 53, 69));
/* 262 */     this.moduleColorMap.put((Module)getModuleByClass(AnvilAura.class), new Color(90, 227, 96));
/* 263 */     this.moduleColorMap.put((Module)getModuleByClass(ArmorMessage.class), new Color(255, 51, 51));
/* 264 */     this.moduleColorMap.put((Module)getModuleByClass(Auto32k.class), new Color(185, 212, 144));
/* 265 */     this.moduleColorMap.put((Module)getModuleByClass(AutoArmor.class), new Color(74, 227, 206));
/* 266 */     this.moduleColorMap.put((Module)getModuleByClass(AutoCrystal.class), new Color(255, 15, 43));
/* 267 */     this.moduleColorMap.put((Module)getModuleByClass(AutoTrap.class), new Color(193, 49, 244));
/* 268 */     this.moduleColorMap.put((Module)getModuleByClass(BedBomb.class), new Color(185, 80, 195));
/* 269 */     this.moduleColorMap.put((Module)getModuleByClass(BowSpam.class), new Color(204, 191, 153));
/* 270 */     this.moduleColorMap.put((Module)getModuleByClass(Crasher.class), new Color(208, 66, 9));
/* 271 */     this.moduleColorMap.put((Module)getModuleByClass(Criticals.class), new Color(204, 151, 184));
/* 272 */     this.moduleColorMap.put((Module)getModuleByClass(HoleFiller.class), new Color(166, 55, 110));
/* 273 */     this.moduleColorMap.put((Module)getModuleByClass(Killaura.class), new Color(255, 37, 0));
/* 274 */     this.moduleColorMap.put((Module)getModuleByClass(Offhand.class), new Color(185, 212, 144));
/* 275 */     this.moduleColorMap.put((Module)getModuleByClass(Selftrap.class), new Color(22, 127, 145));
/* 276 */     this.moduleColorMap.put((Module)getModuleByClass(Surround.class), new Color(100, 0, 150));
/* 277 */     this.moduleColorMap.put((Module)getModuleByClass(Webaura.class), new Color(11, 161, 121));
/* 278 */     this.moduleColorMap.put((Module)getModuleByClass(AntiCrystal.class), new Color(255, 161, 121));
/* 279 */     this.moduleColorMap.put((Module)getModuleByClass(PacketCanceller.class), new Color(155, 186, 115));
/* 280 */     this.moduleColorMap.put((Module)getModuleByClass(AntiVanish.class), new Color(25, 209, 135));
/* 281 */     this.moduleColorMap.put((Module)getModuleByClass(AutoGG.class), new Color(240, 49, 110));
/* 282 */     this.moduleColorMap.put((Module)getModuleByClass(AutoLog.class), new Color(176, 176, 176));
/* 283 */     this.moduleColorMap.put((Module)getModuleByClass(AutoReconnect.class), new Color(17, 85, 153));
/* 284 */     this.moduleColorMap.put((Module)getModuleByClass(BetterPortals.class), new Color(71, 214, 187));
/* 285 */     this.moduleColorMap.put((Module)getModuleByClass(BuildHeight.class), new Color(64, 136, 199));
/* 286 */     this.moduleColorMap.put((Module)getModuleByClass(Bypass.class), new Color(194, 214, 81));
/* 287 */     this.moduleColorMap.put((Module)getModuleByClass(Companion.class), new Color(140, 252, 146));
/* 288 */     this.moduleColorMap.put((Module)getModuleByClass(ChatModifier.class), new Color(255, 59, 216));
/* 289 */     this.moduleColorMap.put((Module)getModuleByClass(Exploits.class), new Color(255, 0, 0));
/* 290 */     this.moduleColorMap.put((Module)getModuleByClass(ExtraTab.class), new Color(161, 113, 173));
/* 291 */     this.moduleColorMap.put((Module)getModuleByClass(Godmode.class), new Color(1, 35, 95));
/* 292 */     this.moduleColorMap.put((Module)getModuleByClass(KitDelete.class), new Color(229, 194, 255));
/* 293 */     this.moduleColorMap.put((Module)getModuleByClass(Logger.class), new Color(186, 0, 109));
/* 294 */     this.moduleColorMap.put((Module)getModuleByClass(MCF.class), new Color(17, 85, 255));
/* 295 */     this.moduleColorMap.put((Module)getModuleByClass(MobOwner.class), new Color(255, 254, 204));
/* 296 */     this.moduleColorMap.put((Module)getModuleByClass(NoAFK.class), new Color(80, 5, 98));
/* 297 */     this.moduleColorMap.put((Module)getModuleByClass(NoHandShake.class), new Color(173, 232, 139));
/* 298 */     this.moduleColorMap.put((Module)getModuleByClass(NoRotate.class), new Color(69, 81, 223));
/* 299 */     this.moduleColorMap.put((Module)getModuleByClass(NoSoundLag.class), new Color(255, 56, 0));
/* 300 */     this.moduleColorMap.put((Module)getModuleByClass(Nuker.class), new Color(152, 169, 17));
/* 301 */     this.moduleColorMap.put((Module)getModuleByClass(PingSpoof.class), new Color(23, 214, 187));
/* 302 */     this.moduleColorMap.put((Module)getModuleByClass(RPC.class), new Color(0, 64, 255));
/* 303 */     this.moduleColorMap.put((Module)getModuleByClass(Spammer.class), new Color(140, 87, 166));
/* 304 */     this.moduleColorMap.put((Module)getModuleByClass(ToolTips.class), new Color(209, 125, 156));
/* 305 */     this.moduleColorMap.put((Module)getModuleByClass(Translator.class), new Color(74, 82, 15));
/* 306 */     this.moduleColorMap.put((Module)getModuleByClass(Tracker.class), new Color(0, 255, 225));
/* 307 */     this.moduleColorMap.put((Module)getModuleByClass(GhastFinder.class), new Color(200, 200, 220));
/* 308 */     this.moduleColorMap.put((Module)getModuleByClass(OffscreenESP.class), new Color(193, 219, 20));
/* 309 */     this.moduleColorMap.put((Module)getModuleByClass(BlockHighlight.class), new Color(103, 182, 224));
/* 310 */     this.moduleColorMap.put((Module)getModuleByClass(CameraClip.class), new Color(247, 169, 107));
/* 311 */     this.moduleColorMap.put((Module)getModuleByClass(Chams.class), new Color(34, 152, 34));
/* 312 */     this.moduleColorMap.put((Module)getModuleByClass(ESP.class), new Color(255, 27, 155));
/* 313 */     this.moduleColorMap.put((Module)getModuleByClass(Fullbright.class), new Color(255, 164, 107));
/* 314 */     this.moduleColorMap.put((Module)getModuleByClass(HandColor.class), new Color(96, 138, 92));
/* 315 */     this.moduleColorMap.put((Module)getModuleByClass(HoleESP.class), new Color(95, 83, 130));
/* 316 */     this.moduleColorMap.put((Module)getModuleByClass(LogoutSpots.class), new Color(2, 135, 134));
/* 317 */     this.moduleColorMap.put((Module)getModuleByClass(Nametags.class), new Color(98, 82, 223));
/* 318 */     this.moduleColorMap.put((Module)getModuleByClass(NoRender.class), new Color(255, 164, 107));
/* 319 */     this.moduleColorMap.put((Module)getModuleByClass(PortalESP.class), new Color(26, 242, 62));
/* 320 */     this.moduleColorMap.put((Module)getModuleByClass(Ranges.class), new Color(144, 212, 196));
/* 321 */     this.moduleColorMap.put((Module)getModuleByClass(Skeleton.class), new Color(219, 219, 219));
/* 322 */     this.moduleColorMap.put((Module)getModuleByClass(SmallShield.class), new Color(145, 223, 187));
/* 323 */     this.moduleColorMap.put((Module)getModuleByClass(StorageESP.class), new Color(97, 81, 223));
/* 324 */     this.moduleColorMap.put((Module)getModuleByClass(Tracer.class), new Color(255, 107, 107));
/* 325 */     this.moduleColorMap.put((Module)getModuleByClass(Trajectories.class), new Color(98, 18, 223));
/* 326 */     this.moduleColorMap.put((Module)getModuleByClass(VoidESP.class), new Color(68, 178, 142));
/* 327 */     this.moduleColorMap.put((Module)getModuleByClass(XRay.class), new Color(217, 118, 37));
/* 328 */     this.moduleColorMap.put((Module)getModuleByClass(AntiLevitate.class), new Color(206, 255, 255));
/* 329 */     this.moduleColorMap.put((Module)getModuleByClass(AutoWalk.class), new Color(153, 153, 170));
/* 330 */     this.moduleColorMap.put((Module)getModuleByClass(ElytraFlight.class), new Color(55, 161, 201));
/* 331 */     this.moduleColorMap.put((Module)getModuleByClass(Flight.class), new Color(186, 164, 178));
/* 332 */     this.moduleColorMap.put((Module)getModuleByClass(HoleTP.class), new Color(68, 178, 142));
/* 333 */     this.moduleColorMap.put((Module)getModuleByClass(IceSpeed.class), new Color(33, 193, 247));
/* 334 */     this.moduleColorMap.put((Module)getModuleByClass(LongJump.class), new Color(228, 27, 213));
/* 335 */     this.moduleColorMap.put((Module)getModuleByClass(NoFall.class), new Color(61, 204, 78));
/* 336 */     this.moduleColorMap.put((Module)getModuleByClass(NoSlowDown.class), new Color(61, 204, 78));
/* 337 */     this.moduleColorMap.put((Module)getModuleByClass(TestPhase.class), new Color(238, 59, 27));
/* 338 */     this.moduleColorMap.put((Module)getModuleByClass(Phase.class), new Color(186, 144, 212));
/* 339 */     this.moduleColorMap.put((Module)getModuleByClass(SafeWalk.class), new Color(182, 186, 164));
/* 340 */     this.moduleColorMap.put((Module)getModuleByClass(Speed.class), new Color(55, 161, 196));
/* 341 */     this.moduleColorMap.put((Module)getModuleByClass(Sprint.class), new Color(148, 184, 142));
/* 342 */     this.moduleColorMap.put((Module)getModuleByClass(Static.class), new Color(86, 53, 98));
/* 343 */     this.moduleColorMap.put((Module)getModuleByClass(Step.class), new Color(144, 212, 203));
/* 344 */     this.moduleColorMap.put((Module)getModuleByClass(StepOld.class), new Color(144, 212, 203));
/* 345 */     this.moduleColorMap.put((Module)getModuleByClass(Strafe.class), new Color(0, 204, 255));
/* 346 */     this.moduleColorMap.put((Module)getModuleByClass(TPSpeed.class), new Color(20, 177, 142));
/* 347 */     this.moduleColorMap.put((Module)getModuleByClass(Velocity.class), new Color(115, 134, 140));
/* 348 */     this.moduleColorMap.put((Module)getModuleByClass(ReverseStep.class), new Color(1, 134, 140));
/* 349 */     this.moduleColorMap.put((Module)getModuleByClass(NoDDoS.class), new Color(67, 191, 181));
/* 350 */     this.moduleColorMap.put((Module)getModuleByClass(Blink.class), new Color(144, 184, 141));
/* 351 */     this.moduleColorMap.put((Module)getModuleByClass(BlockTweaks.class), new Color(89, 223, 235));
/* 352 */     this.moduleColorMap.put((Module)getModuleByClass(EchestBP.class), new Color(255, 243, 30));
/* 353 */     this.moduleColorMap.put((Module)getModuleByClass(FakePlayer.class), new Color(37, 192, 170));
/* 354 */     this.moduleColorMap.put((Module)getModuleByClass(FastPlace.class), new Color(217, 118, 37));
/* 355 */     this.moduleColorMap.put((Module)getModuleByClass(Freecam.class), new Color(206, 232, 128));
/* 356 */     this.moduleColorMap.put((Module)getModuleByClass(Jesus.class), new Color(136, 221, 235));
/* 357 */     this.moduleColorMap.put((Module)getModuleByClass(LiquidInteract.class), new Color(85, 223, 235));
/* 358 */     this.moduleColorMap.put((Module)getModuleByClass(MCP.class), new Color(153, 68, 170));
/* 359 */     this.moduleColorMap.put((Module)getModuleByClass(MultiTask.class), new Color(17, 223, 235));
/* 360 */     this.moduleColorMap.put((Module)getModuleByClass(NoHunger.class), new Color(86, 53, 98));
/* 361 */     this.moduleColorMap.put((Module)getModuleByClass(Reach.class), new Color(9, 223, 187));
/* 362 */     this.moduleColorMap.put((Module)getModuleByClass(Replenish.class), new Color(153, 223, 235));
/* 363 */     this.moduleColorMap.put((Module)getModuleByClass(Scaffold.class), new Color(152, 166, 113));
/* 364 */     this.moduleColorMap.put((Module)getModuleByClass(Speedmine.class), new Color(152, 166, 113));
/* 365 */     this.moduleColorMap.put((Module)getModuleByClass(TimerSpeed.class), new Color(255, 133, 18));
/* 366 */     this.moduleColorMap.put((Module)getModuleByClass(TpsSync.class), new Color(93, 144, 153));
/* 367 */     this.moduleColorMap.put((Module)getModuleByClass(TrueDurability.class), new Color(254, 161, 51));
/* 368 */     this.moduleColorMap.put((Module)getModuleByClass(XCarry.class), new Color(254, 161, 51));
/* 369 */     this.moduleColorMap.put((Module)getModuleByClass(Yaw.class), new Color(115, 39, 141));
/* 370 */     this.moduleColorMap.put((Module)getModuleByClass(Capes.class), new Color(26, 135, 104));
/* 371 */     this.moduleColorMap.put((Module)getModuleByClass(ClickGui.class), new Color(26, 81, 135));
/* 372 */     this.moduleColorMap.put((Module)getModuleByClass(Colors.class), new Color(135, 133, 26));
/* 373 */     this.moduleColorMap.put((Module)getModuleByClass(Components.class), new Color(135, 26, 26));
/* 374 */     this.moduleColorMap.put((Module)getModuleByClass(FontMod.class), new Color(135, 26, 88));
/* 375 */     this.moduleColorMap.put((Module)getModuleByClass(HUD.class), new Color(110, 26, 135));
/* 376 */     this.moduleColorMap.put((Module)getModuleByClass(Managers.class), new Color(26, 90, 135));
/* 377 */     this.moduleColorMap.put((Module)getModuleByClass(Notifications.class), new Color(170, 153, 255));
/* 378 */     this.moduleColorMap.put((Module)getModuleByClass(ServerModule.class), new Color(60, 110, 175));
/* 379 */     this.moduleColorMap.put((Module)getModuleByClass(Media.class), new Color(138, 45, 13));
/* 380 */     this.moduleColorMap.put((Module)getModuleByClass(Screens.class), new Color(165, 89, 101));
/* 381 */     this.moduleColorMap.put((Module)getModuleByClass(StreamerMode.class), new Color(0, 0, 0));
/* 382 */     for (Module module : this.modules) {
/* 383 */       module.animation.start();
/*     */     }
/*     */   }
/*     */   
/*     */   public Module getModuleByName(String name) {
/* 388 */     for (Module module : this.modules) {
/* 389 */       if (!module.getName().equalsIgnoreCase(name))
/* 390 */         continue;  return module;
/*     */     } 
/* 392 */     return null;
/*     */   }
/*     */   
/*     */   public <T extends Module> T getModuleByClass(Class<T> clazz) {
/* 396 */     for (Module module : this.modules) {
/* 397 */       if (!clazz.isInstance(module))
/* 398 */         continue;  return (T)module;
/*     */     } 
/* 400 */     return null;
/*     */   }
/*     */   
/*     */   public void enableModule(Class<Module> clazz) {
/* 404 */     Object module = getModuleByClass(clazz);
/* 405 */     if (module != null) {
/* 406 */       ((Module)module).enable();
/*     */     }
/*     */   }
/*     */   
/*     */   public void disableModule(Class<Module> clazz) {
/* 411 */     Object module = getModuleByClass(clazz);
/* 412 */     if (module != null) {
/* 413 */       ((Module)module).disable();
/*     */     }
/*     */   }
/*     */   
/*     */   public void enableModule(String name) {
/* 418 */     Module module = getModuleByName(name);
/* 419 */     if (module != null) {
/* 420 */       module.enable();
/*     */     }
/*     */   }
/*     */   
/*     */   public void disableModule(String name) {
/* 425 */     Module module = getModuleByName(name);
/* 426 */     if (module != null) {
/* 427 */       module.disable();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isModuleEnabled(String name) {
/* 432 */     Module module = getModuleByName(name);
/* 433 */     return (module != null && module.isOn());
/*     */   }
/*     */   
/*     */   public boolean isModuleEnabled(Class<Module> clazz) {
/* 437 */     Object module = getModuleByClass(clazz);
/* 438 */     return (module != null && ((Module)module).isOn());
/*     */   }
/*     */   
/*     */   public Module getModuleByDisplayName(String displayName) {
/* 442 */     for (Module module : this.modules) {
/* 443 */       if (!module.getDisplayName().equalsIgnoreCase(displayName))
/* 444 */         continue;  return module;
/*     */     } 
/* 446 */     return null;
/*     */   }
/*     */   
/*     */   public ArrayList<Module> getEnabledModules() {
/* 450 */     ArrayList<Module> enabledModules = new ArrayList<>();
/* 451 */     for (Module module : this.modules) {
/* 452 */       if (!module.isEnabled() && !module.isSliding())
/* 453 */         continue;  enabledModules.add(module);
/*     */     } 
/* 455 */     return enabledModules;
/*     */   }
/*     */   
/*     */   public ArrayList<Module> getModulesByCategory(Module.Category category) {
/* 459 */     ArrayList<Module> modulesCategory = new ArrayList<>();
/* 460 */     this.modules.forEach(module -> {
/*     */           if (module.getCategory() == category) {
/*     */             modulesCategory.add(module);
/*     */           }
/*     */         });
/* 465 */     return modulesCategory;
/*     */   }
/*     */   
/*     */   public List<Module.Category> getCategories() {
/* 469 */     return Arrays.asList(Module.Category.values());
/*     */   }
/*     */   
/*     */   public void onLoad() {
/* 473 */     this.modules.stream().filter(Module::listening).forEach(MinecraftForge.EVENT_BUS::register);
/* 474 */     this.modules.forEach(Module::onLoad);
/*     */   }
/*     */   
/*     */   public void onUpdate() {
/* 478 */     this.modules.stream().filter(Feature::isEnabled).forEach(Module::onUpdate);
/*     */   }
/*     */   
/*     */   public void onTick() {
/* 482 */     this.modules.stream().filter(Feature::isEnabled).forEach(Module::onTick);
/*     */   }
/*     */   
/*     */   public void onRender2D(Render2DEvent event) {
/* 486 */     this.modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRender2D(event));
/*     */   }
/*     */   
/*     */   public void onRender3D(Render3DEvent event) {
/* 490 */     this.modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRender3D(event));
/*     */   }
/*     */   
/*     */   public void sortModules(boolean reverse) {
/* 494 */     this.sortedModules = (List<Module>)getEnabledModules().stream().filter(Module::isDrawn).sorted(Comparator.comparing(module -> Integer.valueOf(this.renderer.getStringWidth(module.getFullArrayString()) * (reverse ? -1 : 1)))).collect(Collectors.toList());
/*     */   }
/*     */   
/*     */   public void alphabeticallySortModules() {
/* 498 */     this.alphabeticallySortedModules = (List<Module>)getEnabledModules().stream().filter(Module::isDrawn).sorted(Comparator.comparing(Module::getDisplayName)).collect(Collectors.toList());
/*     */   }
/*     */   
/*     */   public void onLogout() {
/* 502 */     this.modules.forEach(Module::onLogout);
/*     */   }
/*     */   
/*     */   public void onLogin() {
/* 506 */     this.modules.forEach(Module::onLogin);
/*     */   }
/*     */   
/*     */   public void onUnload() {
/* 510 */     this.modules.forEach(MinecraftForge.EVENT_BUS::unregister);
/* 511 */     this.modules.forEach(Module::onUnload);
/*     */   }
/*     */   
/*     */   public void onUnloadPost() {
/* 515 */     for (Module module : this.modules) {
/* 516 */       module.enabled.setValue(Boolean.valueOf(false));
/*     */     }
/*     */   }
/*     */   
/*     */   public void onKeyPressed(int eventKey) {
/* 521 */     if (eventKey == 0 || !Keyboard.getEventKeyState() || mc.field_71462_r instanceof me.earth.phobos.features.gui.PhobosGui) {
/*     */       return;
/*     */     }
/* 524 */     this.modules.forEach(module -> {
/*     */           if (module.getBind().getKey() == eventKey) {
/*     */             module.toggle();
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public List<Module> getAnimationModules(Module.Category category) {
/* 532 */     ArrayList<Module> animationModules = new ArrayList<>();
/* 533 */     for (Module module : getEnabledModules()) {
/* 534 */       if (module.getCategory() != category || module.isDisabled() || !module.isSliding() || !module.isDrawn())
/*     */         continue; 
/* 536 */       animationModules.add(module);
/*     */     } 
/* 538 */     return animationModules;
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\manager\ModuleManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */