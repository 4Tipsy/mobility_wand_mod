
package id.mobility_wand.item;


import id.mobility_wand.Mobility_wand;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;



public class ModItems {

  public static final RegistryKey<ItemGroup> MOBILITY_WAND_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Mobility_wand.MOD_ID, "mobility_wand_group"));
  public static final ItemGroup MOBILITY_WAND_GROUP = FabricItemGroup.builder()
          .displayName(Text.of("Mobility wand mod"))
          .icon(() -> new ItemStack(ModItems.MOBILITY_WAND))
          .build();



  public static final Item MOBILITY_WAND = registerItem(new MobilityWandItem(MobilityWand.INSTANCE, new Item.Settings()), "mobility_wand");



  private static Item registerItem(Item item, String id) {
    return Registry.register(Registries.ITEM, Identifier.of(Mobility_wand.MOD_ID, id), item);
  }


  public static void initialize() {

    // -> group
    Registry.register(Registries.ITEM_GROUP, MOBILITY_WAND_GROUP_KEY, MOBILITY_WAND_GROUP);
    ItemGroupEvents.modifyEntriesEvent(MOBILITY_WAND_GROUP_KEY).register(itemGroup -> {
      itemGroup.add(ModItems.MOBILITY_WAND);
    });

  }
}
