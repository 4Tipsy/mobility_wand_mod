
package id.mobility_wand.item;


import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class MobilityWandItem extends ToolItem {

  public MobilityWandItem(ToolMaterial m, Settings s) {
    super(m, s);
  }

  @Override
  public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
    tooltip.add(Text.translatable("tooltip.mobility_wand.mobility_wand"));
    super.appendTooltip(stack, world, tooltip, context);
  }



  // ACTIONS
  private static String PREV_ACTION = null; // to prevent same action in fly
  @Override
  public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
    ItemStack _itemStack = user.getStackInHand(hand);



    if (world.isClient) {
      return TypedActionResult.pass(user.getStackInHand(hand));
    }
    if (user.getItemCooldownManager().isCoolingDown(_itemStack.getItem())) {
      return TypedActionResult.pass(user.getStackInHand(hand));
    }

    // refresh if on ground
    if (user.isOnGround()) {
      PREV_ACTION = null;
    }



    // BACK DASH
    if (user.isSneaking()) {

      if (!user.isOnGround() && PREV_ACTION == "BACK") {
        return TypedActionResult.pass(user.getStackInHand(hand));
      }

      Vec3d glance = user.getRotationVector();
      user.setVelocity(
              glance.x * -2,
              glance.y * -1,
              glance.z * -2
      );
      user.velocityModified = true;
      world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE, SoundCategory.PLAYERS);
      PREV_ACTION = "BACK";
      _itemStack.damage(1, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
      return TypedActionResult.pass(user.getStackInHand(hand));




    // FRONT DASH
    } else {

      if (!user.isOnGround() && PREV_ACTION == "FORWARD") {
        return TypedActionResult.pass(user.getStackInHand(hand));
      }

      Vec3d glance = user.getRotationVector();
      user.setVelocity(
              glance.x * 2,
              glance.y * 0.8,
              glance.z * 2
      );
      user.velocityModified = true;
      world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS);
      PREV_ACTION = "FORWARD";
      user.animateDamage(2.4f);
      _itemStack.damage(1, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
      return TypedActionResult.pass(user.getStackInHand(hand));
    }



  }

}
