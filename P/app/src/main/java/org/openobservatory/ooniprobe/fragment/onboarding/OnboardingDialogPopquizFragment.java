package org.openobservatory.ooniprobe.fragment.onboarding;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.airbnb.lottie.LottieAnimationView;

import org.openobservatory.ooniprobe.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OnboardingDialogPopquizFragment extends DialogFragment {
	private static final String TITLE_RES_ID = "titleResId";
	private static final String QUESTION_RES_ID = "questionResId";
	@BindView(R.id.title) @Nullable TextView title;
	@BindView(R.id.question) TextView question;
	@BindView(R.id.dialog) LinearLayout dialog;
	@BindView(R.id.animation) LottieAnimationView animation;

	public static OnboardingDialogPopquizFragment newInstance(int titleResId, int questionResId) {
		Bundle args = new Bundle();
		args.putInt(TITLE_RES_ID, titleResId);
		args.putInt(QUESTION_RES_ID, questionResId);
		OnboardingDialogPopquizFragment fragment = new OnboardingDialogPopquizFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onStart() {
		super.onStart();
		getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		WindowManager.LayoutParams windowParams = getDialog().getWindow().getAttributes();
		windowParams.dimAmount = 0.90f;
		windowParams.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
		getDialog().getWindow().setAttributes(windowParams);
	}

	@Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		assert getArguments() != null;
		View v = inflater.inflate(R.layout.fragment_onboarding_dialog_popquiz, container, false);
		ButterKnife.bind(this, v);
		if (title != null)
			title.setText(getArguments().getInt(TITLE_RES_ID));
		question.setText(getArguments().getInt(QUESTION_RES_ID));
		return v;
	}

	@OnClick(R.id.positive) void positiveClick() {
		animation.setBackgroundResource(R.drawable.dialog_green);
		animation.setAnimation("anim/checkMark.json");
		animation.setVisibility(View.VISIBLE);
		dialog.setVisibility(View.INVISIBLE);
		animation.addAnimatorListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				dismiss();
				((OnboardingPopquizInterface) getParentFragment()).onPopquizResult(getArguments().getInt(QUESTION_RES_ID), true);
			}
		});
		animation.playAnimation();
	}

	@OnClick(R.id.negative) void negativeClick() {
		animation.setBackgroundResource(R.drawable.dialog_red);
		animation.setAnimation("anim/crossMark.json");
		animation.setVisibility(View.VISIBLE);
		dialog.setVisibility(View.INVISIBLE);
		animation.addAnimatorListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				dismiss();
				((OnboardingPopquizInterface) getParentFragment()).onPopquizResult(getArguments().getInt(QUESTION_RES_ID), false);
			}
		});
		animation.playAnimation();
	}

	public interface OnboardingPopquizInterface {
		void onPopquizResult(int questionResId, boolean positive);
	}
}
