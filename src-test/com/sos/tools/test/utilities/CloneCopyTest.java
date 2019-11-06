package com.sos.tools.test.utilities;

import junit.framework.Assert;

import org.junit.Test;

import com.sos.tools.test.utilities.dataobjects.Address;
import com.sos.tools.test.utilities.dataobjects.AddressCopy;

public class CloneCopyTest 
{

	
	@Test
	public void testCloneCopy()
	{
		Address orgAddress = new Address();
		orgAddress.setAddress1("5609 Vol Walker Cv");
		orgAddress.setAddress2("SOS Productions");
		orgAddress.setCity("Austin");
		orgAddress.setState("TX");
		orgAddress.setZipp("78749");
		
		AddressCopy newAddress = new AddressCopy();
		newAddress.shallowCopy(orgAddress, true);
		Assert.assertTrue(newAddress.getAddress1().equals(orgAddress.getAddress1()));
		Assert.assertTrue(newAddress.getAddress2().equals(orgAddress.getAddress2()));
		Assert.assertTrue(newAddress.getCity().equals(orgAddress.getCity()));
		Assert.assertTrue(newAddress.getState().equals(orgAddress.getState()));
		Assert.assertTrue(newAddress.getZipp().equals(orgAddress.getZipp()));
		Assert.assertTrue(newAddress.isActive() == orgAddress.isActive());
	}
}
