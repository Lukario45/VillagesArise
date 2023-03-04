package net.mcthunder.VillagesArise.gui;

import net.mcthunder.VillagesArise.VillageRegister;
import net.mcthunder.VillagesArise.VillagesArise;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;


/**
 * Created by Lukario45 on 3/4/2023.
 */
public class TownHallGUI extends AbstractContainerMenu {
    public TownHallGUI(int containerId, Inventory playerInv, FriendlyByteBuf extraData) {
        super(VillageRegister.TOWN_HALL_GUI.get(), containerId);
    }

    @Override
    public ItemStack quickMoveStack(Player p_38941_, int p_38942_) {
        return null;
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return false;
    }
}
