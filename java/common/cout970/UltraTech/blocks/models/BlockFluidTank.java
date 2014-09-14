package common.cout970.UltraTech.blocks.models;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

import common.cout970.UltraTech.TileEntities.fluid.TileEntityFluidTank;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.UT_Tabs;

public class BlockFluidTank extends BlockContainer{

	public BlockFluidTank(Material par2Material) {
		super(par2Material);
		setCreativeTab(UT_Tabs.techTab);
		setHardness(2.5f);
		setStepSound(soundTypeMetal);
		setResistance(20);
		setBlockName("FluidTank");
		setLightOpacity(0);
	}
	
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(Block_Textures.FLUIDTANK);
	}
	
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9)
    {
		ItemStack current = entityplayer.inventory.getCurrentItem();
		if (current != null) {

			FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem(current);

			TileEntityFluidTank tank = (TileEntityFluidTank) world.getTileEntity(i, j, k);

			// Handle filled containers
			if (liquid != null) {
				int qty = tank.fill(null, liquid, true);

				if (qty != 0 && !entityplayer.capabilities.isCreativeMode) {
					entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, consumeItem(current));
				}
				return true;

				// Handle empty containers
			} else {

				FluidStack available = tank.getTankInfo(null)[0].fluid;
				if (available != null) {
					ItemStack filled = FluidContainerRegistry.fillFluidContainer(available, current);

					liquid = FluidContainerRegistry.getFluidForFilledItem(filled);

					if (liquid != null) {
						if (!entityplayer.capabilities.isCreativeMode) {
							if (current.stackSize > 1) {
								if (!entityplayer.inventory.addItemStackToInventory(filled))
									return false;
								else {
									entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, consumeItem(current));
								}
							} else {
								entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, consumeItem(current));
								entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, filled);
							}
						}
						tank.drain(null, liquid.amount, true);
						return true;
					}
				}
			}
		}

		return false;
    }
	
	public static ItemStack consumeItem(ItemStack stack) {
		if (stack.stackSize == 1) {
			if (stack.getItem().hasContainerItem(stack)) {
				return stack.getItem().getContainerItem(stack);
			} else {
				return null;
			}
		} else {
			stack.splitStack(1);

			return stack;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world,int a) {
		return new TileEntityFluidTank();
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}
}
