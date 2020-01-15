package dex.voidflight;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ChorusFruitItem;
import net.minecraft.item.ElytraItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import org.lwjgl.system.CallbackI;

import java.util.concurrent.atomic.AtomicBoolean;

public class VoidFlight implements ModInitializer {
	public static Enchantment VOIDLOVE;
	public static boolean meh = false;

	@Override
	public void onInitialize() {
		VOIDLOVE = Registry.register(
				Registry.ENCHANTMENT,
				new Identifier("voidflight", "voidflight"),
				new LoveOfVoidEnchant(
						Enchantment.Weight.VERY_RARE,
						EnchantmentTarget.ARMOR_CHEST,
						new EquipmentSlot[] {
								EquipmentSlot.CHEST
						}
				)
		);

/*		ClientTickCallback.EVENT.register(t -> {
			if (t.player != null) {
				AtomicBoolean n = new AtomicBoolean(false);
				t.player.inventory.armor.forEach(stack -> {
					if (stack.getItem() instanceof ElytraItem &&
							EnchantmentHelper.getLevel(VOIDLOVE, stack) > 0 &&
							t.player.isFallFlying() &&
							t.player.getMainHandStack().getItem() instanceof ChorusFruitItem) {
						n.set(true);
						meh = true;
						t.player.setInvulnerable(true);
					} else {
						meh = n.get() && n.get();
					}
				});

				if (meh) {
					if (t.player.getPos().y < -5) {
						Vec3d pos = t.player.getPos();
						t.player.setPosition(pos.x, 255, pos.z);
					}

				} else {
					t.player.setInvulnerable(false);
				}
			}
		});*/

		ServerTickCallback.EVENT.register(t -> {
			for (ServerPlayerEntity player : t.getPlayerManager().getPlayerList()) {
				if (player != null) {
					AtomicBoolean n = new AtomicBoolean(false);
					player.inventory.armor.forEach(stack -> {
						if (stack.getItem() instanceof ElytraItem &&
								EnchantmentHelper.getLevel(VOIDLOVE, stack) > 0 &&
								player.isFallFlying() &&
								player.getMainHandStack().getItem() instanceof ChorusFruitItem) {
							n.set(true);
							meh = true;
							player.setInvulnerable(true);
						} else {
							meh = n.get() && n.get();
						}
					});

					if (meh) {
						if (player.getPos().y < -5) {
							Vec3d pos = player.getPos();
							player.teleport(pos.x, 255, pos.z);
						}

					} else {
						player.setInvulnerable(false);
					}
				}
			}
		});

	}
}
