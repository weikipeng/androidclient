/*
 * Kontalk Android client
 * Copyright (C) 2017 Kontalk Devteam <devteam@kontalk.org>

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.kontalk.position;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.kontalk.GlideApp;
import org.kontalk.R;
import org.kontalk.util.ViewUtils;

/**
 * Location row
 *
 * @author andreacappelli
 */

public class LocationRow extends RelativeLayout {

    private ImageView mImageView;
    private TextView mNameTextView;
    private TextView mAddressTextView;
    private boolean mNeedDivider;

    public LocationRow(Context context) {
        this(context, null);
    }

    public LocationRow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LocationRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LocationRow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init();
    }

    private void init() {
        inflate(getContext(), R.layout.location_row, this);

        setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.white));

        mImageView = (ImageView) findViewById(R.id.image);
        mNameTextView = (TextView) findViewById(R.id.name_text);
        mAddressTextView = (TextView) findViewById(R.id.address_text);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(ViewUtils.dp(getContext(), 56) + (mNeedDivider ? 1 : 0), MeasureSpec.EXACTLY));
    }

    public void setLocation(String iconUrl, String nameText, String addressText, boolean needDivider) {
        mNeedDivider = needDivider;
        GlideApp.with(getContext()).load(iconUrl).into(mImageView);
        mNameTextView.setText(nameText);
        mAddressTextView.setText(addressText);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mNeedDivider) {
            Paint paint = new Paint();
            paint.setStrokeWidth(1);
            paint.setColor(ContextCompat.getColor(getContext(), R.color.divider));
            canvas.drawLine(ViewUtils.dp(getContext(), 72), getHeight() - 1, getWidth(), getHeight() - 1, paint);
        }
    }

}
