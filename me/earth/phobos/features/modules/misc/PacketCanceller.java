/*     */ package me.earth.phobos.features.modules.misc;
/*     */ 
/*     */ import me.earth.phobos.event.events.PacketEvent;
/*     */ import me.earth.phobos.features.command.Command;
/*     */ import me.earth.phobos.features.modules.Module;
/*     */ import me.earth.phobos.features.setting.Setting;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PacketCanceller
/*     */   extends Module
/*     */ {
/* 117 */   private Setting<Mode> mode = register(new Setting("Packets", Mode.CLIENT));
/* 118 */   private Setting<Integer> page = register(new Setting("SPackets", Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(10), v -> (this.mode.getValue() == Mode.SERVER)));
/* 119 */   private Setting<Integer> pages = register(new Setting("CPackets", Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(4), v -> (this.mode.getValue() == Mode.CLIENT)));
/* 120 */   private Setting<Boolean> AdvancementInfo = register(new Setting("AdvancementInfo", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 1)));
/* 121 */   private Setting<Boolean> Animation = register(new Setting("Animation", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 1)));
/* 122 */   private Setting<Boolean> BlockAction = register(new Setting("BlockAction", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 1)));
/* 123 */   private Setting<Boolean> BlockBreakAnim = register(new Setting("BlockBreakAnim", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 1)));
/* 124 */   private Setting<Boolean> BlockChange = register(new Setting("BlockChange", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 1)));
/* 125 */   private Setting<Boolean> Camera = register(new Setting("Camera", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 1)));
/* 126 */   private Setting<Boolean> ChangeGameState = register(new Setting("ChangeGameState", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 1)));
/* 127 */   private Setting<Boolean> Chat = register(new Setting("Chat", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 1)));
/* 128 */   private Setting<Boolean> ChunkData = register(new Setting("ChunkData", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 2)));
/* 129 */   private Setting<Boolean> CloseWindow = register(new Setting("CloseWindow", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 2)));
/* 130 */   private Setting<Boolean> CollectItem = register(new Setting("CollectItem", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 2)));
/* 131 */   private Setting<Boolean> CombatEvent = register(new Setting("Combatevent", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 2)));
/* 132 */   private Setting<Boolean> ConfirmTransaction = register(new Setting("ConfirmTransaction", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 2)));
/* 133 */   private Setting<Boolean> Cooldown = register(new Setting("Cooldown", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 2)));
/* 134 */   private Setting<Boolean> CustomPayload = register(new Setting("CustomPayload", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 2)));
/* 135 */   private Setting<Boolean> CustomSound = register(new Setting("CustomSound", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 2)));
/* 136 */   private Setting<Boolean> DestroyEntities = register(new Setting("DestroyEntities", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 3)));
/* 137 */   private Setting<Boolean> Disconnect = register(new Setting("Disconnect", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 3)));
/* 138 */   private Setting<Boolean> DisplayObjective = register(new Setting("DisplayObjective", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 3)));
/* 139 */   private Setting<Boolean> Effect = register(new Setting("Effect", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 3)));
/* 140 */   private Setting<Boolean> Entity = register(new Setting("Entity", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 3)));
/* 141 */   private Setting<Boolean> EntityAttach = register(new Setting("EntityAttach", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 3)));
/* 142 */   private Setting<Boolean> EntityEffect = register(new Setting("EntityEffect", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 3)));
/* 143 */   private Setting<Boolean> EntityEquipment = register(new Setting("EntityEquipment", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 3)));
/* 144 */   private Setting<Boolean> EntityHeadLook = register(new Setting("EntityHeadLook", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 4)));
/* 145 */   private Setting<Boolean> EntityMetadata = register(new Setting("EntityMetadata", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 4)));
/* 146 */   private Setting<Boolean> EntityProperties = register(new Setting("EntityProperties", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 4)));
/* 147 */   private Setting<Boolean> EntityStatus = register(new Setting("EntityStatus", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 4)));
/* 148 */   private Setting<Boolean> EntityTeleport = register(new Setting("EntityTeleport", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 4)));
/* 149 */   private Setting<Boolean> EntityVelocity = register(new Setting("EntityVelocity", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 4)));
/* 150 */   private Setting<Boolean> Explosion = register(new Setting("Explosion", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 4)));
/* 151 */   private Setting<Boolean> HeldItemChange = register(new Setting("HeldItemChange", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 4)));
/* 152 */   private Setting<Boolean> JoinGame = register(new Setting("JoinGame", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 5)));
/* 153 */   private Setting<Boolean> KeepAlive = register(new Setting("KeepAlive", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 5)));
/* 154 */   private Setting<Boolean> Maps = register(new Setting("Maps", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 5)));
/* 155 */   private Setting<Boolean> MoveVehicle = register(new Setting("MoveVehicle", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 5)));
/* 156 */   private Setting<Boolean> MultiBlockChange = register(new Setting("MultiBlockChange", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 5)));
/* 157 */   private Setting<Boolean> OpenWindow = register(new Setting("OpenWindow", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 5)));
/* 158 */   private Setting<Boolean> Particles = register(new Setting("Particles", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 5)));
/* 159 */   private Setting<Boolean> PlaceGhostRecipe = register(new Setting("PlaceGhostRecipe", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 5)));
/* 160 */   private Setting<Boolean> PlayerAbilities = register(new Setting("PlayerAbilities", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 6)));
/* 161 */   private Setting<Boolean> PlayerListHeaderFooter = register(new Setting("PlayerListHeaderFooter", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 6)));
/* 162 */   private Setting<Boolean> PlayerListItem = register(new Setting("PlayerListItem", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 6)));
/* 163 */   private Setting<Boolean> PlayerPosLook = register(new Setting("PlayerPosLook", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 6)));
/* 164 */   private Setting<Boolean> RecipeBook = register(new Setting("RecipeBook", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 6)));
/* 165 */   private Setting<Boolean> RemoveEntityEffect = register(new Setting("RemoveEntityEffect", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 6)));
/* 166 */   private Setting<Boolean> ResourcePackSend = register(new Setting("ResourcePackSend", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 6)));
/* 167 */   private Setting<Boolean> Respawn = register(new Setting("Respawn", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 6)));
/* 168 */   private Setting<Boolean> ScoreboardObjective = register(new Setting("ScoreboardObjective", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 7)));
/* 169 */   private Setting<Boolean> SelectAdvancementsTab = register(new Setting("SelectAdvancementsTab", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 7)));
/* 170 */   private Setting<Boolean> ServerDifficulty = register(new Setting("ServerDifficulty", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 7)));
/* 171 */   private Setting<Boolean> SetExperience = register(new Setting("SetExperience", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 7)));
/* 172 */   private Setting<Boolean> SetPassengers = register(new Setting("SetPassengers", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 7)));
/* 173 */   private Setting<Boolean> SetSlot = register(new Setting("SetSlot", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 7)));
/* 174 */   private Setting<Boolean> SignEditorOpen = register(new Setting("SignEditorOpen", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 7)));
/* 175 */   private Setting<Boolean> SoundEffect = register(new Setting("SoundEffect", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 7)));
/* 176 */   private Setting<Boolean> SpawnExperienceOrb = register(new Setting("SpawnExperienceOrb", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 8)));
/* 177 */   private Setting<Boolean> SpawnGlobalEntity = register(new Setting("SpawnGlobalEntity", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 8)));
/* 178 */   private Setting<Boolean> SpawnMob = register(new Setting("SpawnMob", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 8)));
/* 179 */   private Setting<Boolean> SpawnObject = register(new Setting("SpawnObject", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 8)));
/* 180 */   private Setting<Boolean> SpawnPainting = register(new Setting("SpawnPainting", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 8)));
/* 181 */   private Setting<Boolean> SpawnPlayer = register(new Setting("SpawnPlayer", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 8)));
/* 182 */   private Setting<Boolean> SpawnPosition = register(new Setting("SpawnPosition", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 8)));
/* 183 */   private Setting<Boolean> Statistics = register(new Setting("Statistics", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 8)));
/* 184 */   private Setting<Boolean> TabComplete = register(new Setting("TabComplete", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 9)));
/* 185 */   private Setting<Boolean> Teams = register(new Setting("Teams", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 9)));
/* 186 */   private Setting<Boolean> TimeUpdate = register(new Setting("TimeUpdate", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 9)));
/* 187 */   private Setting<Boolean> Title = register(new Setting("Title", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 9)));
/* 188 */   private Setting<Boolean> UnloadChunk = register(new Setting("UnloadChunk", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 9)));
/* 189 */   private Setting<Boolean> UpdateBossInfo = register(new Setting("UpdateBossInfo", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 9)));
/* 190 */   private Setting<Boolean> UpdateHealth = register(new Setting("UpdateHealth", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 9)));
/* 191 */   private Setting<Boolean> UpdateScore = register(new Setting("UpdateScore", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 9)));
/* 192 */   private Setting<Boolean> UpdateTileEntity = register(new Setting("UpdateTileEntity", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 10)));
/* 193 */   private Setting<Boolean> UseBed = register(new Setting("UseBed", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 10)));
/* 194 */   private Setting<Boolean> WindowItems = register(new Setting("WindowItems", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 10)));
/* 195 */   private Setting<Boolean> WindowProperty = register(new Setting("WindowProperty", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 10)));
/* 196 */   private Setting<Boolean> WorldBorder = register(new Setting("WorldBorder", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.SERVER && ((Integer)this.page.getValue()).intValue() == 10)));
/* 197 */   private Setting<Boolean> Animations = register(new Setting("Animations", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 1)));
/* 198 */   private Setting<Boolean> ChatMessage = register(new Setting("ChatMessage", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 1)));
/* 199 */   private Setting<Boolean> ClickWindow = register(new Setting("ClickWindow", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 1)));
/* 200 */   private Setting<Boolean> ClientSettings = register(new Setting("ClientSettings", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 1)));
/* 201 */   private Setting<Boolean> ClientStatus = register(new Setting("ClientStatus", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 1)));
/* 202 */   private Setting<Boolean> CloseWindows = register(new Setting("CloseWindows", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 1)));
/* 203 */   private Setting<Boolean> ConfirmTeleport = register(new Setting("ConfirmTeleport", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 1)));
/* 204 */   private Setting<Boolean> ConfirmTransactions = register(new Setting("ConfirmTransactions", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 1)));
/* 205 */   private Setting<Boolean> CreativeInventoryAction = register(new Setting("CreativeInventoryAction", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 2)));
/* 206 */   private Setting<Boolean> CustomPayloads = register(new Setting("CustomPayloads", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 2)));
/* 207 */   private Setting<Boolean> EnchantItem = register(new Setting("EnchantItem", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 2)));
/* 208 */   private Setting<Boolean> EntityAction = register(new Setting("EntityAction", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 2)));
/* 209 */   private Setting<Boolean> HeldItemChanges = register(new Setting("HeldItemChanges", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 2)));
/* 210 */   private Setting<Boolean> Input = register(new Setting("Input", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 2)));
/* 211 */   private Setting<Boolean> KeepAlives = register(new Setting("KeepAlives", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 2)));
/* 212 */   private Setting<Boolean> PlaceRecipe = register(new Setting("PlaceRecipe", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 2)));
/* 213 */   private Setting<Boolean> Player = register(new Setting("Player", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 3)));
/* 214 */   private Setting<Boolean> PlayerAbility = register(new Setting("PlayerAbility", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 3)));
/* 215 */   private Setting<Boolean> PlayerDigging = register(new Setting("PlayerDigging", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.page.getValue()).intValue() == 3)));
/* 216 */   private Setting<Boolean> PlayerTryUseItem = register(new Setting("PlayerTryUseItem", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 3)));
/* 217 */   private Setting<Boolean> PlayerTryUseItemOnBlock = register(new Setting("TryUseItemOnBlock", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 3)));
/* 218 */   private Setting<Boolean> RecipeInfo = register(new Setting("RecipeInfo", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 3)));
/* 219 */   private Setting<Boolean> ResourcePackStatus = register(new Setting("ResourcePackStatus", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 3)));
/* 220 */   private Setting<Boolean> SeenAdvancements = register(new Setting("SeenAdvancements", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 3)));
/* 221 */   private Setting<Boolean> PlayerPackets = register(new Setting("PlayerPackets", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 4)));
/* 222 */   private Setting<Boolean> Spectate = register(new Setting("Spectate", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 4)));
/* 223 */   private Setting<Boolean> SteerBoat = register(new Setting("SteerBoat", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 4)));
/* 224 */   private Setting<Boolean> TabCompletion = register(new Setting("TabCompletion", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 4)));
/* 225 */   private Setting<Boolean> UpdateSign = register(new Setting("UpdateSign", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 4)));
/* 226 */   private Setting<Boolean> UseEntity = register(new Setting("UseEntity", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 4)));
/* 227 */   private Setting<Boolean> VehicleMove = register(new Setting("VehicleMove", Boolean.valueOf(false), v -> (this.mode.getValue() == Mode.CLIENT && ((Integer)this.pages.getValue()).intValue() == 4)));
/* 228 */   private int hudAmount = 0;
/*     */   
/*     */   public PacketCanceller() {
/* 231 */     super("PacketCanceller", "Cancels packets", Module.Category.MISC, true, false, false);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPacketSend(PacketEvent.Send event) {
/* 236 */     if (!isEnabled()) {
/*     */       return;
/*     */     }
/* 239 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketAnimation && ((Boolean)this.Animations.getValue()).booleanValue()) {
/* 240 */       event.setCanceled(true);
/*     */     }
/* 242 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketChatMessage && ((Boolean)this.ChatMessage.getValue()).booleanValue()) {
/* 243 */       event.setCanceled(true);
/*     */     }
/* 245 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketClickWindow && ((Boolean)this.ClickWindow.getValue()).booleanValue()) {
/* 246 */       event.setCanceled(true);
/*     */     }
/* 248 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketClientSettings && ((Boolean)this.ClientSettings.getValue()).booleanValue()) {
/* 249 */       event.setCanceled(true);
/*     */     }
/* 251 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketClientStatus && ((Boolean)this.ClientStatus.getValue()).booleanValue()) {
/* 252 */       event.setCanceled(true);
/*     */     }
/* 254 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketCloseWindow && ((Boolean)this.CloseWindows.getValue()).booleanValue()) {
/* 255 */       event.setCanceled(true);
/*     */     }
/* 257 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketConfirmTeleport && ((Boolean)this.ConfirmTeleport.getValue()).booleanValue()) {
/* 258 */       event.setCanceled(true);
/*     */     }
/* 260 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketConfirmTransaction && ((Boolean)this.ConfirmTransactions.getValue()).booleanValue()) {
/* 261 */       event.setCanceled(true);
/*     */     }
/* 263 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketCreativeInventoryAction && ((Boolean)this.CreativeInventoryAction.getValue()).booleanValue()) {
/* 264 */       event.setCanceled(true);
/*     */     }
/* 266 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketCustomPayload && ((Boolean)this.CustomPayloads.getValue()).booleanValue()) {
/* 267 */       event.setCanceled(true);
/*     */     }
/* 269 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketEnchantItem && ((Boolean)this.EnchantItem.getValue()).booleanValue()) {
/* 270 */       event.setCanceled(true);
/*     */     }
/* 272 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketEntityAction && ((Boolean)this.EntityAction.getValue()).booleanValue()) {
/* 273 */       event.setCanceled(true);
/*     */     }
/* 275 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketHeldItemChange && ((Boolean)this.HeldItemChanges.getValue()).booleanValue()) {
/* 276 */       event.setCanceled(true);
/*     */     }
/* 278 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketInput && ((Boolean)this.Input.getValue()).booleanValue()) {
/* 279 */       event.setCanceled(true);
/*     */     }
/* 281 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketKeepAlive && ((Boolean)this.KeepAlives.getValue()).booleanValue()) {
/* 282 */       event.setCanceled(true);
/*     */     }
/* 284 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketPlaceRecipe && ((Boolean)this.PlaceRecipe.getValue()).booleanValue()) {
/* 285 */       event.setCanceled(true);
/*     */     }
/* 287 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketPlayer && ((Boolean)this.Player.getValue()).booleanValue()) {
/* 288 */       event.setCanceled(true);
/*     */     }
/* 290 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketPlayerAbilities && ((Boolean)this.PlayerAbility.getValue()).booleanValue()) {
/* 291 */       event.setCanceled(true);
/*     */     }
/* 293 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketPlayerDigging && ((Boolean)this.PlayerDigging.getValue()).booleanValue()) {
/* 294 */       event.setCanceled(true);
/*     */     }
/* 296 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketPlayerTryUseItem && ((Boolean)this.PlayerTryUseItem.getValue()).booleanValue()) {
/* 297 */       event.setCanceled(true);
/*     */     }
/* 299 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock && ((Boolean)this.PlayerTryUseItemOnBlock.getValue()).booleanValue()) {
/* 300 */       event.setCanceled(true);
/*     */     }
/* 302 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketRecipeInfo && ((Boolean)this.RecipeInfo.getValue()).booleanValue()) {
/* 303 */       event.setCanceled(true);
/*     */     }
/* 305 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketResourcePackStatus && ((Boolean)this.ResourcePackStatus.getValue()).booleanValue()) {
/* 306 */       event.setCanceled(true);
/*     */     }
/* 308 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketSeenAdvancements && ((Boolean)this.SeenAdvancements.getValue()).booleanValue()) {
/* 309 */       event.setCanceled(true);
/*     */     }
/* 311 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketSpectate && ((Boolean)this.Spectate.getValue()).booleanValue()) {
/* 312 */       event.setCanceled(true);
/*     */     }
/* 314 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketSteerBoat && ((Boolean)this.SteerBoat.getValue()).booleanValue()) {
/* 315 */       event.setCanceled(true);
/*     */     }
/* 317 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketTabComplete && ((Boolean)this.TabCompletion.getValue()).booleanValue()) {
/* 318 */       event.setCanceled(true);
/*     */     }
/* 320 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketUpdateSign && ((Boolean)this.UpdateSign.getValue()).booleanValue()) {
/* 321 */       event.setCanceled(true);
/*     */     }
/* 323 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketUseEntity && ((Boolean)this.UseEntity.getValue()).booleanValue()) {
/* 324 */       event.setCanceled(true);
/*     */     }
/* 326 */     if (event.getPacket() instanceof net.minecraft.network.play.client.CPacketVehicleMove && ((Boolean)this.VehicleMove.getValue()).booleanValue()) {
/* 327 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPacketReceive(PacketEvent.Receive event) {
/* 333 */     if (!isEnabled()) {
/*     */       return;
/*     */     }
/* 336 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketAdvancementInfo && ((Boolean)this.AdvancementInfo.getValue()).booleanValue()) {
/* 337 */       event.setCanceled(true);
/*     */     }
/* 339 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketAnimation && ((Boolean)this.Animation.getValue()).booleanValue()) {
/* 340 */       event.setCanceled(true);
/*     */     }
/* 342 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketBlockAction && ((Boolean)this.BlockAction.getValue()).booleanValue()) {
/* 343 */       event.setCanceled(true);
/*     */     }
/* 345 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketBlockBreakAnim && ((Boolean)this.BlockBreakAnim.getValue()).booleanValue()) {
/* 346 */       event.setCanceled(true);
/*     */     }
/* 348 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketBlockChange && ((Boolean)this.BlockChange.getValue()).booleanValue()) {
/* 349 */       event.setCanceled(true);
/*     */     }
/* 351 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketCamera && ((Boolean)this.Camera.getValue()).booleanValue()) {
/* 352 */       event.setCanceled(true);
/*     */     }
/* 354 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketChangeGameState && ((Boolean)this.ChangeGameState.getValue()).booleanValue()) {
/* 355 */       event.setCanceled(true);
/*     */     }
/* 357 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketChat && ((Boolean)this.Chat.getValue()).booleanValue()) {
/* 358 */       event.setCanceled(true);
/*     */     }
/* 360 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketChunkData && ((Boolean)this.ChunkData.getValue()).booleanValue()) {
/* 361 */       event.setCanceled(true);
/*     */     }
/* 363 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketCloseWindow && ((Boolean)this.CloseWindow.getValue()).booleanValue()) {
/* 364 */       event.setCanceled(true);
/*     */     }
/* 366 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketCollectItem && ((Boolean)this.CollectItem.getValue()).booleanValue()) {
/* 367 */       event.setCanceled(true);
/*     */     }
/* 369 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketCombatEvent && ((Boolean)this.CombatEvent.getValue()).booleanValue()) {
/* 370 */       event.setCanceled(true);
/*     */     }
/* 372 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketConfirmTransaction && ((Boolean)this.ConfirmTransaction.getValue()).booleanValue()) {
/* 373 */       event.setCanceled(true);
/*     */     }
/* 375 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketCooldown && ((Boolean)this.Cooldown.getValue()).booleanValue()) {
/* 376 */       event.setCanceled(true);
/*     */     }
/* 378 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketCustomPayload && ((Boolean)this.CustomPayload.getValue()).booleanValue()) {
/* 379 */       event.setCanceled(true);
/*     */     }
/* 381 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketCustomSound && ((Boolean)this.CustomSound.getValue()).booleanValue()) {
/* 382 */       event.setCanceled(true);
/*     */     }
/* 384 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketDestroyEntities && ((Boolean)this.DestroyEntities.getValue()).booleanValue()) {
/* 385 */       event.setCanceled(true);
/*     */     }
/* 387 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketDisconnect && ((Boolean)this.Disconnect.getValue()).booleanValue()) {
/* 388 */       event.setCanceled(true);
/*     */     }
/* 390 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketChunkData && ((Boolean)this.ChunkData.getValue()).booleanValue()) {
/* 391 */       event.setCanceled(true);
/*     */     }
/* 393 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketCloseWindow && ((Boolean)this.CloseWindow.getValue()).booleanValue()) {
/* 394 */       event.setCanceled(true);
/*     */     }
/* 396 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketCollectItem && ((Boolean)this.CollectItem.getValue()).booleanValue()) {
/* 397 */       event.setCanceled(true);
/*     */     }
/* 399 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketDisplayObjective && ((Boolean)this.DisplayObjective.getValue()).booleanValue()) {
/* 400 */       event.setCanceled(true);
/*     */     }
/* 402 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketEffect && ((Boolean)this.Effect.getValue()).booleanValue()) {
/* 403 */       event.setCanceled(true);
/*     */     }
/* 405 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketEntity && ((Boolean)this.Entity.getValue()).booleanValue()) {
/* 406 */       event.setCanceled(true);
/*     */     }
/* 408 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketEntityAttach && ((Boolean)this.EntityAttach.getValue()).booleanValue()) {
/* 409 */       event.setCanceled(true);
/*     */     }
/* 411 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketEntityEffect && ((Boolean)this.EntityEffect.getValue()).booleanValue()) {
/* 412 */       event.setCanceled(true);
/*     */     }
/* 414 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketEntityEquipment && ((Boolean)this.EntityEquipment.getValue()).booleanValue()) {
/* 415 */       event.setCanceled(true);
/*     */     }
/* 417 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketEntityHeadLook && ((Boolean)this.EntityHeadLook.getValue()).booleanValue()) {
/* 418 */       event.setCanceled(true);
/*     */     }
/* 420 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketEntityMetadata && ((Boolean)this.EntityMetadata.getValue()).booleanValue()) {
/* 421 */       event.setCanceled(true);
/*     */     }
/* 423 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketEntityProperties && ((Boolean)this.EntityProperties.getValue()).booleanValue()) {
/* 424 */       event.setCanceled(true);
/*     */     }
/* 426 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketEntityStatus && ((Boolean)this.EntityStatus.getValue()).booleanValue()) {
/* 427 */       event.setCanceled(true);
/*     */     }
/* 429 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketEntityTeleport && ((Boolean)this.EntityTeleport.getValue()).booleanValue()) {
/* 430 */       event.setCanceled(true);
/*     */     }
/* 432 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketEntityVelocity && ((Boolean)this.EntityVelocity.getValue()).booleanValue()) {
/* 433 */       event.setCanceled(true);
/*     */     }
/* 435 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketExplosion && ((Boolean)this.Explosion.getValue()).booleanValue()) {
/* 436 */       event.setCanceled(true);
/*     */     }
/* 438 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketHeldItemChange && ((Boolean)this.HeldItemChange.getValue()).booleanValue()) {
/* 439 */       event.setCanceled(true);
/*     */     }
/* 441 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketJoinGame && ((Boolean)this.JoinGame.getValue()).booleanValue()) {
/* 442 */       event.setCanceled(true);
/*     */     }
/* 444 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketKeepAlive && ((Boolean)this.KeepAlive.getValue()).booleanValue()) {
/* 445 */       event.setCanceled(true);
/*     */     }
/* 447 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketMaps && ((Boolean)this.Maps.getValue()).booleanValue()) {
/* 448 */       event.setCanceled(true);
/*     */     }
/* 450 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketMoveVehicle && ((Boolean)this.MoveVehicle.getValue()).booleanValue()) {
/* 451 */       event.setCanceled(true);
/*     */     }
/* 453 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketMultiBlockChange && ((Boolean)this.MultiBlockChange.getValue()).booleanValue()) {
/* 454 */       event.setCanceled(true);
/*     */     }
/* 456 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketOpenWindow && ((Boolean)this.OpenWindow.getValue()).booleanValue()) {
/* 457 */       event.setCanceled(true);
/*     */     }
/* 459 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketParticles && ((Boolean)this.Particles.getValue()).booleanValue()) {
/* 460 */       event.setCanceled(true);
/*     */     }
/* 462 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketPlaceGhostRecipe && ((Boolean)this.PlaceGhostRecipe.getValue()).booleanValue()) {
/* 463 */       event.setCanceled(true);
/*     */     }
/* 465 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketPlayerAbilities && ((Boolean)this.PlayerAbilities.getValue()).booleanValue()) {
/* 466 */       event.setCanceled(true);
/*     */     }
/* 468 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketPlayerListHeaderFooter && ((Boolean)this.PlayerListHeaderFooter.getValue()).booleanValue()) {
/* 469 */       event.setCanceled(true);
/*     */     }
/* 471 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketPlayerListItem && ((Boolean)this.PlayerListItem.getValue()).booleanValue()) {
/* 472 */       event.setCanceled(true);
/*     */     }
/* 474 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketPlayerPosLook && ((Boolean)this.PlayerPosLook.getValue()).booleanValue()) {
/* 475 */       event.setCanceled(true);
/*     */     }
/* 477 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketRecipeBook && ((Boolean)this.RecipeBook.getValue()).booleanValue()) {
/* 478 */       event.setCanceled(true);
/*     */     }
/* 480 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketRemoveEntityEffect && ((Boolean)this.RemoveEntityEffect.getValue()).booleanValue()) {
/* 481 */       event.setCanceled(true);
/*     */     }
/* 483 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketResourcePackSend && ((Boolean)this.ResourcePackSend.getValue()).booleanValue()) {
/* 484 */       event.setCanceled(true);
/*     */     }
/* 486 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketRespawn && ((Boolean)this.Respawn.getValue()).booleanValue()) {
/* 487 */       event.setCanceled(true);
/*     */     }
/* 489 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketScoreboardObjective && ((Boolean)this.ScoreboardObjective.getValue()).booleanValue()) {
/* 490 */       event.setCanceled(true);
/*     */     }
/* 492 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketSelectAdvancementsTab && ((Boolean)this.SelectAdvancementsTab.getValue()).booleanValue()) {
/* 493 */       event.setCanceled(true);
/*     */     }
/* 495 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketServerDifficulty && ((Boolean)this.ServerDifficulty.getValue()).booleanValue()) {
/* 496 */       event.setCanceled(true);
/*     */     }
/* 498 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketSetExperience && ((Boolean)this.SetExperience.getValue()).booleanValue()) {
/* 499 */       event.setCanceled(true);
/*     */     }
/* 501 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketSetPassengers && ((Boolean)this.SetPassengers.getValue()).booleanValue()) {
/* 502 */       event.setCanceled(true);
/*     */     }
/* 504 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketSetSlot && ((Boolean)this.SetSlot.getValue()).booleanValue()) {
/* 505 */       event.setCanceled(true);
/*     */     }
/* 507 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketSignEditorOpen && ((Boolean)this.SignEditorOpen.getValue()).booleanValue()) {
/* 508 */       event.setCanceled(true);
/*     */     }
/* 510 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketSoundEffect && ((Boolean)this.SoundEffect.getValue()).booleanValue()) {
/* 511 */       event.setCanceled(true);
/*     */     }
/* 513 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketSpawnExperienceOrb && ((Boolean)this.SpawnExperienceOrb.getValue()).booleanValue()) {
/* 514 */       event.setCanceled(true);
/*     */     }
/* 516 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketSpawnGlobalEntity && ((Boolean)this.SpawnGlobalEntity.getValue()).booleanValue()) {
/* 517 */       event.setCanceled(true);
/*     */     }
/* 519 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketSpawnMob && ((Boolean)this.SpawnMob.getValue()).booleanValue()) {
/* 520 */       event.setCanceled(true);
/*     */     }
/* 522 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketSpawnObject && ((Boolean)this.SpawnObject.getValue()).booleanValue()) {
/* 523 */       event.setCanceled(true);
/*     */     }
/* 525 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketSpawnPainting && ((Boolean)this.SpawnPainting.getValue()).booleanValue()) {
/* 526 */       event.setCanceled(true);
/*     */     }
/* 528 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketSpawnPlayer && ((Boolean)this.SpawnPlayer.getValue()).booleanValue()) {
/* 529 */       event.setCanceled(true);
/*     */     }
/* 531 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketSpawnPosition && ((Boolean)this.SpawnPosition.getValue()).booleanValue()) {
/* 532 */       event.setCanceled(true);
/*     */     }
/* 534 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketStatistics && ((Boolean)this.Statistics.getValue()).booleanValue()) {
/* 535 */       event.setCanceled(true);
/*     */     }
/* 537 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketTabComplete && ((Boolean)this.TabComplete.getValue()).booleanValue()) {
/* 538 */       event.setCanceled(true);
/*     */     }
/* 540 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketTeams && ((Boolean)this.Teams.getValue()).booleanValue()) {
/* 541 */       event.setCanceled(true);
/*     */     }
/* 543 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketTimeUpdate && ((Boolean)this.TimeUpdate.getValue()).booleanValue()) {
/* 544 */       event.setCanceled(true);
/*     */     }
/* 546 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketTitle && ((Boolean)this.Title.getValue()).booleanValue()) {
/* 547 */       event.setCanceled(true);
/*     */     }
/* 549 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketUnloadChunk && ((Boolean)this.UnloadChunk.getValue()).booleanValue()) {
/* 550 */       event.setCanceled(true);
/*     */     }
/* 552 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketUpdateBossInfo && ((Boolean)this.UpdateBossInfo.getValue()).booleanValue()) {
/* 553 */       event.setCanceled(true);
/*     */     }
/* 555 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketUpdateHealth && ((Boolean)this.UpdateHealth.getValue()).booleanValue()) {
/* 556 */       event.setCanceled(true);
/*     */     }
/* 558 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketUpdateScore && ((Boolean)this.UpdateScore.getValue()).booleanValue()) {
/* 559 */       event.setCanceled(true);
/*     */     }
/* 561 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketUpdateTileEntity && ((Boolean)this.UpdateTileEntity.getValue()).booleanValue()) {
/* 562 */       event.setCanceled(true);
/*     */     }
/* 564 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketUseBed && ((Boolean)this.UseBed.getValue()).booleanValue()) {
/* 565 */       event.setCanceled(true);
/*     */     }
/* 567 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketWindowItems && ((Boolean)this.WindowItems.getValue()).booleanValue()) {
/* 568 */       event.setCanceled(true);
/*     */     }
/* 570 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketWindowProperty && ((Boolean)this.WindowProperty.getValue()).booleanValue()) {
/* 571 */       event.setCanceled(true);
/*     */     }
/* 573 */     if (event.getPacket() instanceof net.minecraft.network.play.server.SPacketWorldBorder && ((Boolean)this.WorldBorder.getValue()).booleanValue()) {
/* 574 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/* 580 */     String standart = "§aAntiPackets On!§f Cancelled Packets: ";
/* 581 */     StringBuilder text = new StringBuilder(standart);
/* 582 */     if (!this.settings.isEmpty()) {
/* 583 */       for (Setting setting : this.settings) {
/* 584 */         if (!(setting.getValue() instanceof Boolean) || !((Boolean)setting.getValue()).booleanValue() || setting.getName().equalsIgnoreCase("Enabled") || setting.getName().equalsIgnoreCase("drawn"))
/* 585 */           continue;  String name = setting.getName();
/* 586 */         text.append(name).append(", ");
/*     */       } 
/*     */     }
/* 589 */     if (text.toString().equals(standart)) {
/* 590 */       Command.sendMessage("§aAntiPackets On!§f Currently not cancelling any Packets.");
/*     */     } else {
/* 592 */       String output = removeLastChar(removeLastChar(text.toString()));
/* 593 */       Command.sendMessage(output);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 599 */     int amount = 0;
/* 600 */     if (!this.settings.isEmpty()) {
/* 601 */       for (Setting setting : this.settings) {
/* 602 */         if (!(setting.getValue() instanceof Boolean) || !((Boolean)setting.getValue()).booleanValue() || setting.getName().equalsIgnoreCase("Enabled") || setting.getName().equalsIgnoreCase("drawn"))
/* 603 */           continue;  amount++;
/*     */       } 
/*     */     }
/* 606 */     this.hudAmount = amount;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDisplayInfo() {
/* 611 */     if (this.hudAmount == 0) {
/* 612 */       return "";
/*     */     }
/* 614 */     return this.hudAmount + "";
/*     */   }
/*     */   
/*     */   public String removeLastChar(String str) {
/* 618 */     if (str != null && str.length() > 0) {
/* 619 */       str = str.substring(0, str.length() - 1);
/*     */     }
/* 621 */     return str;
/*     */   }
/*     */   
/*     */   public enum Mode {
/* 625 */     CLIENT,
/* 626 */     SERVER;
/*     */   }
/*     */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\misc\PacketCanceller.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */