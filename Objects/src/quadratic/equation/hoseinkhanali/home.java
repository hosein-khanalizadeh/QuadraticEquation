package quadratic.equation.hoseinkhanali;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class home extends Activity implements B4AActivity{
	public static home mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "quadratic.equation.hoseinkhanali", "quadratic.equation.hoseinkhanali.home");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (home).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "quadratic.equation.hoseinkhanali", "quadratic.equation.hoseinkhanali.home");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "quadratic.equation.hoseinkhanali.home", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (home) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (home) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return home.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (home) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (home) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static boolean _a_check = false;
public static boolean _b_check = false;
public static boolean _c_check = false;
public static String _empty = "";
public static String _a_s = "";
public static String _b_s = "";
public static String _c_s = "";
public static String _delta = "";
public anywheresoftware.b4a.objects.LabelWrapper _title = null;
public anywheresoftware.b4a.objects.LabelWrapper _back = null;
public anywheresoftware.b4a.objects.EditTextWrapper _a = null;
public anywheresoftware.b4a.objects.LabelWrapper _x2 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _b = null;
public anywheresoftware.b4a.objects.LabelWrapper _x = null;
public anywheresoftware.b4a.objects.EditTextWrapper _c = null;
public anywheresoftware.b4a.objects.LabelWrapper _e = null;
public anywheresoftware.b4a.objects.ButtonWrapper _run = null;
public anywheresoftware.b4a.objects.LabelWrapper _x_1_l = null;
public anywheresoftware.b4a.objects.LabelWrapper _x_1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _x_2_l = null;
public anywheresoftware.b4a.objects.LabelWrapper _x_2 = null;
public quadratic.equation.hoseinkhanali.main _main = null;
public quadratic.equation.hoseinkhanali.starter _starter = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _a_textchanged(String _old,String _new) throws Exception{
 //BA.debugLineNum = 44;BA.debugLine="Sub a_TextChanged (Old As String, New As String)";
 //BA.debugLineNum = 45;BA.debugLine="If a.Text <> empty Then";
if ((mostCurrent._a.getText()).equals(_empty) == false) { 
 //BA.debugLineNum = 46;BA.debugLine="a_check = True";
_a_check = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 48;BA.debugLine="a_check = False";
_a_check = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 50;BA.debugLine="If a_check = True And b_check = True And c_check";
if (_a_check==anywheresoftware.b4a.keywords.Common.True && _b_check==anywheresoftware.b4a.keywords.Common.True && _c_check) { 
 //BA.debugLineNum = 51;BA.debugLine="run.Enabled = True";
mostCurrent._run.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 53;BA.debugLine="run.Enabled = False";
mostCurrent._run.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 55;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 31;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 32;BA.debugLine="Activity.LoadLayout(\"Home_Frame\")";
mostCurrent._activity.LoadLayout("Home_Frame",mostCurrent.activityBA);
 //BA.debugLineNum = 33;BA.debugLine="empty = a.Text";
_empty = mostCurrent._a.getText();
 //BA.debugLineNum = 34;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 40;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 42;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 36;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 38;BA.debugLine="End Sub";
return "";
}
public static String  _b_textchanged(String _old,String _new) throws Exception{
 //BA.debugLineNum = 57;BA.debugLine="Sub b_TextChanged (Old As String, New As String)";
 //BA.debugLineNum = 58;BA.debugLine="If b.Text <> empty Then";
if ((mostCurrent._b.getText()).equals(_empty) == false) { 
 //BA.debugLineNum = 59;BA.debugLine="b_check = True";
_b_check = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 61;BA.debugLine="b_check = False";
_b_check = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 63;BA.debugLine="If a_check = True And b_check = True And c_check";
if (_a_check==anywheresoftware.b4a.keywords.Common.True && _b_check==anywheresoftware.b4a.keywords.Common.True && _c_check) { 
 //BA.debugLineNum = 64;BA.debugLine="run.Enabled = True";
mostCurrent._run.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 66;BA.debugLine="run.Enabled = False";
mostCurrent._run.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 68;BA.debugLine="End Sub";
return "";
}
public static String  _c_textchanged(String _old,String _new) throws Exception{
 //BA.debugLineNum = 70;BA.debugLine="Sub c_TextChanged (Old As String, New As String)";
 //BA.debugLineNum = 71;BA.debugLine="If c.Text <> empty Then";
if ((mostCurrent._c.getText()).equals(_empty) == false) { 
 //BA.debugLineNum = 72;BA.debugLine="c_check = True";
_c_check = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 74;BA.debugLine="c_check = False";
_c_check = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 76;BA.debugLine="If a_check = True And b_check = True And c_check";
if (_a_check==anywheresoftware.b4a.keywords.Common.True && _b_check==anywheresoftware.b4a.keywords.Common.True && _c_check) { 
 //BA.debugLineNum = 77;BA.debugLine="run.Enabled = True";
mostCurrent._run.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 79;BA.debugLine="run.Enabled = False";
mostCurrent._run.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 81;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 16;BA.debugLine="Private title As Label";
mostCurrent._title = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private back As Label";
mostCurrent._back = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private a As EditText";
mostCurrent._a = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private x2 As Label";
mostCurrent._x2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private b As EditText";
mostCurrent._b = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private x As Label";
mostCurrent._x = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private c As EditText";
mostCurrent._c = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private e As Label";
mostCurrent._e = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private run As Button";
mostCurrent._run = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private x_1_l As Label";
mostCurrent._x_1_l = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private x_1 As Label";
mostCurrent._x_1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private x_2_l As Label";
mostCurrent._x_2_l = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private x_2 As Label";
mostCurrent._x_2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 4;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 5;BA.debugLine="Dim a_check As Boolean";
_a_check = false;
 //BA.debugLineNum = 6;BA.debugLine="Dim b_check As Boolean";
_b_check = false;
 //BA.debugLineNum = 7;BA.debugLine="Dim c_check As Boolean";
_c_check = false;
 //BA.debugLineNum = 8;BA.debugLine="Dim empty As String";
_empty = "";
 //BA.debugLineNum = 9;BA.debugLine="Dim a_s As String";
_a_s = "";
 //BA.debugLineNum = 10;BA.debugLine="Dim b_s As String";
_b_s = "";
 //BA.debugLineNum = 11;BA.debugLine="Dim c_s As String";
_c_s = "";
 //BA.debugLineNum = 12;BA.debugLine="Dim delta As String";
_delta = "";
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
public static String  _run_click() throws Exception{
 //BA.debugLineNum = 83;BA.debugLine="Sub run_Click";
 //BA.debugLineNum = 84;BA.debugLine="x_1.Text = empty";
mostCurrent._x_1.setText(BA.ObjectToCharSequence(_empty));
 //BA.debugLineNum = 85;BA.debugLine="x_2.Text = empty";
mostCurrent._x_2.setText(BA.ObjectToCharSequence(_empty));
 //BA.debugLineNum = 86;BA.debugLine="a_s = a.Text";
_a_s = mostCurrent._a.getText();
 //BA.debugLineNum = 87;BA.debugLine="b_s = b.Text";
_b_s = mostCurrent._b.getText();
 //BA.debugLineNum = 88;BA.debugLine="c_s = c.Text";
_c_s = mostCurrent._c.getText();
 //BA.debugLineNum = 89;BA.debugLine="If a.Text <> 0 Then";
if ((mostCurrent._a.getText()).equals(BA.NumberToString(0)) == false) { 
 //BA.debugLineNum = 90;BA.debugLine="delta = Sqrt(Power(b_s, 2) - (4 * a_s * c_s))";
_delta = BA.NumberToString(anywheresoftware.b4a.keywords.Common.Sqrt(anywheresoftware.b4a.keywords.Common.Power((double)(Double.parseDouble(_b_s)),2)-(4*(double)(Double.parseDouble(_a_s))*(double)(Double.parseDouble(_c_s)))));
 //BA.debugLineNum = 91;BA.debugLine="x_1.Text = ((0 - b_s) + delta) / (2 * a_s)";
mostCurrent._x_1.setText(BA.ObjectToCharSequence(((0-(double)(Double.parseDouble(_b_s)))+(double)(Double.parseDouble(_delta)))/(double)(2*(double)(Double.parseDouble(_a_s)))));
 //BA.debugLineNum = 92;BA.debugLine="x_2.Text = ((0 - b_s) - delta) / (2 * a_s)";
mostCurrent._x_2.setText(BA.ObjectToCharSequence(((0-(double)(Double.parseDouble(_b_s)))-(double)(Double.parseDouble(_delta)))/(double)(2*(double)(Double.parseDouble(_a_s)))));
 };
 //BA.debugLineNum = 94;BA.debugLine="End Sub";
return "";
}
}
