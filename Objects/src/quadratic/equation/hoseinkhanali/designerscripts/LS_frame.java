package quadratic.equation.hoseinkhanali.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_frame{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 2;BA.debugLine="AutoScaleAll"[Frame/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
//BA.debugLineNum = 4;BA.debugLine="info.Height = 60%y"[Frame/General script]
views.get("info").vw.setHeight((int)((60d / 100 * height)));
//BA.debugLineNum = 5;BA.debugLine="info.Width = 50%x"[Frame/General script]
views.get("info").vw.setWidth((int)((50d / 100 * width)));
//BA.debugLineNum = 6;BA.debugLine="info.Top = 20%y"[Frame/General script]
views.get("info").vw.setTop((int)((20d / 100 * height)));
//BA.debugLineNum = 7;BA.debugLine="info.Left = 25%x"[Frame/General script]
views.get("info").vw.setLeft((int)((25d / 100 * width)));

}
}