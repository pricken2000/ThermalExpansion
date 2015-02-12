package cofh.thermalexpansion.block.ender;

import cofh.core.CoFHProps;
import cofh.thermalexpansion.ThermalExpansion;
import cofh.thermalexpansion.block.TileRSControl;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;

public class TileTesseractBound extends TileRSControl implements ISidedInventory {

	public static void initialize() {

		GameRegistry.registerTileEntity(TileTesseract.class, "thermalexpansion.Tesseract");
	}

	public static void configure() {

		String comment = "Enable this to allow for Strongboxes to be secure inventories.";
		enableSecurity = ThermalExpansion.config.get("block.security", "Strongbox.Secure", enableSecurity, comment);
	}

	public static boolean enableSecurity = true;

	String owner = null;// CoFHProps.DEFAULT_OWNER;
	private AccessMode access = AccessMode.PUBLIC;

	int[] slots = new int[0];
	boolean[] insert = new boolean[27];
	boolean[] extract = new boolean[27];

	/* Client-Side Only */
	public boolean canAccess = true;

	@Override
	public String getName() {

		return "tile.thermalexpansion.ender.tesseract.name";
	}

	@Override
	public int getType() {

		return 1;
	}

	/* ISidedInventory */
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {

		return slots;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {

		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {

		return false;
	}

	/* ISecurable */
	@Override
	public boolean setAccess(AccessMode access) {

		this.access = access;
		sendUpdatePacket(Side.SERVER);
		return true;
	}

	@Override
	public AccessMode getAccess() {

		return access;
	}

	@Override
	public boolean setOwnerName(String name) {

		if (owner.equals(CoFHProps.DEFAULT_OWNER)) {
			owner = name;
			return true;
		}
		return false;
	}

	@Override
	public String getOwnerName() {

		return owner;
	}

}