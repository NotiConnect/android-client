package zoidberg.wilson.doc.noticonnectandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

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

	@Override
	public
	void onCreate(
		@Nullable
			Bundle savedInstanceState
					 )
	{
		super.onCreate( savedInstanceState );
	}

	@Nullable
	@Override
	public
	View onCreateView(
		LayoutInflater inflater,
		@Nullable
			ViewGroup container,
		@Nullable
			Bundle savedInstanceState
						  )
	{
		View view
			  = inflater.inflate(
			R.layout.fragment_login,
			container,
			false
									  );
		// find view by ids
		return view;
	}
// constructors
	// protected methods
	// private methods
	// private classes
}
