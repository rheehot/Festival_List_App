package org.techtown.festival;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kakao.auth.ApiErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MypageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        Button Logout = findViewById(R.id.mypage_Logout);
        Button Signout = findViewById(R.id.mypage_Signout);

        // 로그아웃 버튼을 눌렀을 때
        Logout.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "로그아웃 성공!", Toast.LENGTH_SHORT).show();

                // 로그아웃 부분
                UserManagement.getInstance()
                        .requestLogout(new LogoutResponseCallback() {
                            @Override
                            public void onCompleteLogout() {
                                // 로그아웃이 되었다면 로그인 액티비티로 넘어간다.
                                Intent intent = new Intent(MypageActivity.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        });
            }
        });

        // 회원 탈퇴 버튼을 눌렀을 때
        Signout.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MypageActivity.this).setMessage("회원 탈퇴를 진행하시겠습니까?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() { // Yes면
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 연결 끊기 부분
                                UserManagement.getInstance()
                                        .requestUnlink(new UnLinkResponseCallback() {
                                            @Override
                                            public void onSessionClosed(ErrorResult errorResult) {
                                                Toast.makeText(getApplicationContext(), "에러: "+errorResult+"로그인 세션이 닫혔습니다.", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(MypageActivity.this, LoginActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }

                                            @Override
                                            public  void onFailure(ErrorResult errorResult) {
                                                int error = errorResult.getErrorCode();
                                                if(error == ApiErrorCode.CLIENT_ERROR_CODE) {
                                                    Toast.makeText(getApplicationContext(), "에러: "+error+"\n네트워크 연결이 불안정합니다.", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(getApplicationContext(), "에러: "+error+"\n회원 탈퇴에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                            @Override
                                            public void onSuccess(Long result) {
                                                Toast.makeText(getApplicationContext(), "성공: "+result+"\n회원 탈퇴 되었습니다.", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(MypageActivity.this, LoginActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        });
                                dialog.dismiss(); // 팝업창 끄기
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() { // No면
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        });
    }
}
