package com.meals.bruce.meal_test01;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class tab3 extends Fragment {
    View v;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.tab3, container, false);


        if (getArguments() != null) {
            Log.d("send1", getArguments().getString("comment"));
        }

        AsyncTask3 task3 = new AsyncTask3();
        task3.execute();

        return v;

    }

    //AsyncTask
    class AsyncTask3 extends AsyncTask<Object, Void, Void> {
        ArrayList<DTO_com> dto;
        DAO_com dao;
        ImageButton allview, bfview, lcview, dnview;
        ImageButton write;
        ListView listview;
        ListViewAdapter adapter;
        int flag = -1;

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Void doInBackground(Object... objects) {
            super.onPreExecute();
            dao = new DAO_com();
            dto = dao.select();

            allview = (ImageButton) v.findViewById(R.id.allview);
            bfview = (ImageButton) v.findViewById(R.id.bfview);
            lcview = (ImageButton) v.findViewById(R.id.lcview);
            dnview = (ImageButton) v.findViewById(R.id.dnview);

            write = (ImageButton) v.findViewById(R.id.write);

            listview = (ListView) v.findViewById(R.id.listview);

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... params) {

        }

        @Override
        protected void onPostExecute(Void a) {
            // Adapter 생성
            adapter = new ListViewAdapter();

            // 리스트뷰 참조 및 Adapter달기

            listview.setAdapter(adapter);

            //listview setOnclick
            listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View v, final int i, long l) {
                    if (flag == -1) {
                        final DeleteDialog deleteDialog = new DeleteDialog(getContext());
                        deleteDialog.setDeleteListener(new DeleteDialogListener() {
                            @Override
                            public void onPositiveClicked(String pass) {
                                if (pass.equals(dto.get(i).getPass())) {

                                    new Thread() {
                                        public void run() {
                                            dao.delete(dto, i);
                                            Handler handler = new Handler(Looper.getMainLooper());
                                            handler.post(new Thread() {
                                                @Override // loop 바인딩 Handler -> uI수정
                                                public void run() {
                                                    adapter.clear();
                                                    flag = addLine(adapter, dto);
                                                    adapter.notifyDataSetChanged();
                                                }
                                            });
                                        }
                                    }.start();

                                    Toast toast = Toast.makeText(getContext(), "삭제되었습니다.", Toast.LENGTH_SHORT);
                                    toast.show();

                                } else {
                                    Toast toast = Toast.makeText(getContext(), "비밀번호를 확인해주세요", Toast.LENGTH_LONG);
                                    toast.show();
                                }
                                InputMethodManager immhide = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                                immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                            }

                            @Override
                            public void onNegativeClicked() {

                            }
                        });
                        deleteDialog.show();
                    }
                    return false;
                }
            });


            flag = addLine(adapter, dto);

            allview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.clear();
                    flag = addLine(adapter, dto);
                    adapter.notifyDataSetChanged();
                }
            });

            bfview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.clear();
                    flag = addLineTime(adapter, dto, 0);
                    adapter.notifyDataSetChanged();

                }
            });
            lcview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.clear();
                    flag = addLineTime(adapter, dto, 1);
                    adapter.notifyDataSetChanged();

                }
            });
            dnview.setOnClickListener(new ImageView.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.clear();
                    flag = addLineTime(adapter, dto, 2);
                    adapter.notifyDataSetChanged();
                }
            });

            write.setOnClickListener(new ImageButton.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WriteDialog dialog = new WriteDialog(getContext());
                    dialog.setDialogListener((new DialogListener() {
                        @Override
                        public void onPositiveClicked(int time, int mood, String comment, String pass) {
                            dto.add(0, new DTO_com(time, mood, comment, pass));

                            new Thread() {
                                public void run() {
                                    dao.insert(dto);
                                    Handler handler = new Handler(Looper.getMainLooper());
                                    handler.post(new Thread() {
                                        @Override // loop 바인딩 Handler -> uI수정
                                        public void run() {
                                            adapter.clear();
                                            flag = addLine(adapter, dto);
                                            adapter.notifyDataSetChanged();
                                        }
                                    });
                                }
                            }.start();

                            adapter.clear();
                            flag = addLine(adapter, dto);
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onNegativeClicked() {
                        }
                    }));
                    dialog.show();
                }
            });
        }
    }

    public int addLine(ListViewAdapter a, ArrayList<DTO_com> b) {
        //0~2
        int timeList[] = {R.drawable.morning, R.drawable.sun, R.drawable.night};
        //0~4
        int moodList[] = {R.drawable.mood1, R.drawable.mood2, R.drawable.mood3, R.drawable.mood4, R.drawable.mood5};
            for (DTO_com i : b) {
                Log.d("why???????",i.getCom());
                a.addItem(ContextCompat.getDrawable(getContext(), timeList[i.getTime()]),
                        ContextCompat.getDrawable(getContext(), moodList[i.getGrade()]), i.getCom());
            }

        return -1;
    }

    public int addLineTime(ListViewAdapter a, ArrayList<DTO_com> b, int type) {

        ArrayList<ListViewItem> items = new ArrayList();
        //0~2
        int timeList[] = {R.drawable.morning, R.drawable.sun, R.drawable.night};
        //0~4
        int moodList[] = {R.drawable.mood1, R.drawable.mood2, R.drawable.mood3, R.drawable.mood4, R.drawable.mood5};
        for (DTO_com i : b) {
            if (i.getTime() == type) {
                ListViewItem item = new ListViewItem();
                item.setIcon1(ContextCompat.getDrawable(getContext(), timeList[i.getTime()]));
                item.setIcon2(ContextCompat.getDrawable(getContext(), moodList[i.getGrade()]));
                item.setAppr(i.getCom());
                a.addItem(item);
            }
        }
        return type;
    }

}