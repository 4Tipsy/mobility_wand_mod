
package id.mobility_wand.item;


import net.minecraft.client.item.TooltipContext;
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




  @Override
  public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {



    if (world.isClient) {
      return TypedActionResult.pass(user.getStackInHand(hand));
    }

    if (user.isFallFlying()) {
      return TypedActionResult.pass(user.getStackInHand(hand));
    }



    // BACK DASH
    if (user.isSneaking()) {
      Vec3d glance = user.getRotationVector();
      user.setVelocity(
              glance.x * -2,
              glance.y * -1,
              glance.z * -2
      );
      user.velocityModified = true;
      world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE, SoundCategory.PLAYERS);
      return TypedActionResult.pass(user.getStackInHand(hand));



    // FRONT DASH
    } else {
      Vec3d glance = user.getRotationVector();
      user.setVelocity(
              glance.x * 2,
              glance.y * 1,
              glance.z * 2
      );
      user.velocityModified = true;
      world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_IRON_DOOR_OPEN, SoundCategory.PLAYERS);
      return TypedActionResult.pass(user.getStackInHand(hand));
    }



  }

}
