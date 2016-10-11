package edu.kvcc.cis298.w.noticonnectandroidclient;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Purpose:
 * collect user login info and attempt to login
 * transition to main fragment on successful login
 *
 * TODO: add java doc and parameters
 * 
 */
public
class LoginFragment
	extends Fragment
{
	// public variables
	// protected variables
	// private variables
	// view variables
	private AutoCompleteTextView
		mEmailView;
	private EditText
		mPasswordView;

	// public methods
	public static LoginFragment newInstance()
	{
		Bundle args = new Bundle( );
		// put serializables in
		//args.putSerializable( key, value );

		LoginFragment fragment = new LoginFragment();
		fragment.setArguments( args );
		return fragment;
	}
	// constructors
	// protected methods
	// private methods
	// private classes
}
