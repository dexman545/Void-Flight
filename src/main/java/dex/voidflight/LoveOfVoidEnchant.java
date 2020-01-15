package dex.voidflight;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class LoveOfVoidEnchant extends Enchantment {

    protected LoveOfVoidEnchant(Weight weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    @Override
    public int getMaximumLevel()
    {
        return 1;
    }
}