package org.techtown.festival;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.kakao.auth.ApiErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.network.ErrorResult;
import com.kakao.util.exception.KakaoException;

import androidx.annotation.Nullable;

// 로그인 다루는 코드
public class LoginActivity extends Activity {

    // 세션 콜백 구현
    private ISessionCallback sessionCallback = new ISessionCallback() {
        @Override
        public void onSessionOpened() { // 로그인 세션이 정상적으로 작동
            UserManagement.getInstance().me(new MeV2ResponseCallback() {
                @Override
                public void onFailure(ErrorResult errorResult) {
                    int error = errorResult.getErrorCode();

                    if (error == ApiErrorCode.CLIENT_ERROR_CODE) {
                        Toast.makeText(getApplicationContext(), "에러: "+error+"\n네트워크 연결이 불안정합니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "에러: "+errorResult.getErrorMessage()+"\n로그인 오류가 발생했습니다: ", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    Toast.makeText(getApplicationContext(), "에러: "+errorResult.getErrorMessage()+"\n세션이 닫혔습니다.", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(MeV2Response result) {
                    // Intent intent = new Intent(getApplicationContext(), InformationActivity.class); // 나중에 병합하고 MainActivity로 수정
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("ID", ""+result.getId());
                    startActivity(intent);
                    finish();
                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) { // 로그인 세션이 정상적으로 작동하지 않을 경우
            Toast.makeText(getApplicationContext(), "에러: "+exception.toString()+"\n로그인 도중 오류가 발생했습니다. ", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 세션 콜백 등록
        Session.getCurrentSession().addCallback(sessionCallback);
        Session.getCurrentSession().checkAndImplicitOpen();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 세션 콜백 삭제
        Session.getCurrentSession().removeCallback(sessionCallback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        // 카카오톡|스토리 간편로그인 실행 결과를 받아서 SDK로 전달
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
