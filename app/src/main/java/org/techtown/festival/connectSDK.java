package org.techtown.festival;

import android.app.Application;
import android.content.Context;

import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;
// kakao SDK를 앱과 연결하는 코드
public class connectSDK extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // SDK 초기화
        KakaoSDK.init(new KakaoAdapter() {

            @Override
            public IApplicationConfig getApplicationConfig() {
                return new IApplicationConfig() {
                    @Override
                    public Context getApplicationContext() {
                        return connectSDK.this;
                    }
                };
            }
        });
    }
    private static volatile connectSDK instance = null;

    private abstract class KakaoSDKAdapter extends KakaoAdapter {
        public ISessionConfig getSessionConfig() {
            return new ISessionConfig() {
                @Override
                public AuthType[] getAuthTypes() {
                    return new AuthType[] {AuthType.KAKAO_LOGIN_ALL}; // 모든 로그인 방식 사용 가능
                }

                @Override
                public boolean isUsingWebviewTimer() {
                    return false;
                }

                @Override
                public boolean isSecureMode() {
                    return false;
                }

                @Override
                public ApprovalType getApprovalType() {
                    return ApprovalType.INDIVIDUAL;
                }

                @Override
                public boolean isSaveFormData() { // 빼먹으면 오류남
                    // kakao SDK에서 사용되는 Webview에서 email 입력폼의 데이터를 저장할지 여부를 결정
                   return true;
                }
            };
        }
    }
}
