package dex.voidflight.mixin;

import dex.voidflight.VoidFlight;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockState.class)
public abstract class BlockStateMixin {


	@Shadow public abstract Block getBlock();

	@Inject(at=@At("HEAD"), method = "getCollisionShape(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/EntityContext;)Lnet/minecraft/util/shape/VoxelShape;", cancellable = true)
	public void getCollisionShape(BlockView view, BlockPos pos, EntityContext ePos, CallbackInfoReturnable<VoxelShape> cir) {
		if (VoidFlight.meh) {
			cir.setReturnValue(VoxelShapes.empty());
		}
		//System.out.println("meh");
	}

	@Inject(at=@At("HEAD"), method = "getCollisionShape(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/shape/VoxelShape;", cancellable = true)
	public void getCollisionShape(BlockView view, BlockPos pos, CallbackInfoReturnable<VoxelShape> cir) {
		if (VoidFlight.meh) {
			//System.out.println("test");
			cir.setReturnValue(VoxelShapes.empty());
		}
	}

	@Inject(at=@At("HEAD"), method = "onEntityCollision")
	public void onEntityCollision(World world, BlockPos pos, Entity entity, CallbackInfo ci) {
		if (entity instanceof PlayerEntity) {

		}
	}

}

