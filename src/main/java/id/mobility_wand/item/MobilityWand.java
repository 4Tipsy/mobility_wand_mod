
package id.mobility_wand.item;


import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;




public class MobilityWand implements ToolMaterial {

  public static final MobilityWand INSTANCE = new MobilityWand();

  @Override
  public int getDurability() {
    return 100;
  }

  @Override
  public float getMiningSpeedMultiplier() {
    return 0;
  }

  @Override
  public float getAttackDamage() {
    return 1.5F;
  }

  @Override
  public int getMiningLevel() {
    return 0;
  }

  @Override
  public int getEnchantability() {
    return 0;
  }

  @Override
  public Ingredient getRepairIngredient() {
    return Ingredient.ofItems(Items.GOLD_INGOT);
  }


}