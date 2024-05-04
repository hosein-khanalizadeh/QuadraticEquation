B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=7.8
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: False
Sub Process_Globals
	Dim a_check As Boolean
	Dim b_check As Boolean
	Dim c_check As Boolean
	Dim empty As String
	Dim a_s As String
	Dim b_s As String
	Dim c_s As String
	Dim delta As String
End Sub

Sub Globals
	Private title As Label
	Private back As Label
	Private a As EditText
	Private x2 As Label
	Private b As EditText
	Private x As Label
	Private c As EditText
	Private e As Label
	Private run As Button
	Private x_1_l As Label
	Private x_1 As Label
	Private x_2_l As Label
	Private x_2 As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("Home_Frame")
	empty = a.Text
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub a_TextChanged (Old As String, New As String)
	If a.Text <> empty Then
		a_check = True
	Else
		a_check = False
	End If
	If a_check = True And b_check = True And c_check Then
		run.Enabled = True
	Else
		run.Enabled = False
	End If
End Sub

Sub b_TextChanged (Old As String, New As String)
	If b.Text <> empty Then
		b_check = True
	Else
		b_check = False
	End If
	If a_check = True And b_check = True And c_check Then
		run.Enabled = True
	Else
		run.Enabled = False
	End If
End Sub

Sub c_TextChanged (Old As String, New As String)
	If c.Text <> empty Then
		c_check = True
	Else
		c_check = False
	End If
	If a_check = True And b_check = True And c_check Then
		run.Enabled = True
	Else
		run.Enabled = False
	End If
End Sub

Sub run_Click
	x_1.Text = empty
	x_2.Text = empty
	a_s = a.Text
	b_s = b.Text
	c_s = c.Text
	If a.Text <> 0 Then
		delta = Sqrt(Power(b_s, 2) - (4 * a_s * c_s))
		x_1.Text = ((0 - b_s) + delta) / (2 * a_s)
		x_2.Text = ((0 - b_s) - delta) / (2 * a_s)
	End If
End Sub