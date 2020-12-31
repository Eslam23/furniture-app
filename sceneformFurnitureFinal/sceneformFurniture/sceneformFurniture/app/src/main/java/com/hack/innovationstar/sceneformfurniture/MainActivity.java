package com.hack.innovationstar.sceneformfurniture;

import android.app.AlertDialog;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity {

    private ArFragment fragment;

    private Uri selectedObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.sceneform_fragment);

        InitializeGallery();

        fragment.setOnTapArPlaneListener(
                (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {

                    if (plane.getType() != Plane.Type.HORIZONTAL_UPWARD_FACING){
                        return;
                    }

                    Anchor anchor = hitResult.createAnchor();

                    placeObject(fragment, anchor, selectedObject);

                }
        );
    }

    private void InitializeGallery(){
        LinearLayout gallery = findViewById(R.id.gallery_layout);

        ImageView chair = new ImageView(this);
        chair.setImageResource(R.drawable.chair_thumb);
        chair.setContentDescription("chair");
        chair.setOnClickListener(view -> {selectedObject = Uri.parse("chair.sfb");});
        gallery.addView(chair);

        ImageView couch = new ImageView(this);
        couch.setImageResource(R.drawable.couch_thumb);
        couch.setContentDescription("couch");
        couch.setOnClickListener(view -> {selectedObject = Uri.parse("couch.sfb");});
        gallery.addView(couch);

        ImageView lamp = new ImageView(this);
        lamp.setImageResource(R.drawable.lamp_thumb);
        lamp.setContentDescription("lamp");
        lamp.setOnClickListener(view -> {selectedObject = Uri.parse("lamp.sfb");});
        gallery.addView(lamp);


        ImageView dresser = new ImageView(this);
        dresser.setImageResource(R.drawable.dresser);
        dresser.setContentDescription("dresser");
        dresser.setOnClickListener(view -> {selectedObject = Uri.parse("dresser.sfb");});
        gallery.addView(dresser);

        ImageView couch2 = new ImageView(this);
        couch2.setImageResource(R.drawable.sofa);
        couch2.setContentDescription("couch2");
        couch2.setOnClickListener(view -> {selectedObject = Uri.parse("archive.sfb");});
        gallery.addView(couch2);

        ImageView ladder = new ImageView(this);
        ladder.setImageResource(R.drawable.ladder);
        ladder.setContentDescription("ladder");
        ladder.setOnClickListener(view -> {selectedObject = Uri.parse("ladder.sfb");});
        gallery.addView(ladder);


        ImageView Nightstand = new ImageView(this);
        Nightstand.setImageResource(R.drawable.nightstand);
        Nightstand.setContentDescription("Nightstand");
        Nightstand.setOnClickListener(view -> {selectedObject = Uri.parse("Nightstand.sfb");});
        gallery.addView(Nightstand);

        ImageView Paper_Towel = new ImageView(this);
        Paper_Towel.setImageResource(R.drawable.papertowel);
        Paper_Towel.setContentDescription("Paper Towel");
        Paper_Towel.setOnClickListener(view -> {selectedObject = Uri.parse("Paper_Towel.sfb");});
        gallery.addView(Paper_Towel);

        ImageView Kitchen_Table = new ImageView(this);
        Kitchen_Table.setImageResource(R.drawable.kitchen);
        Kitchen_Table.setContentDescription("Kitchen Table");
        Kitchen_Table.setOnClickListener(view -> {selectedObject = Uri.parse("Kitchen_Table.sfb");});
        gallery.addView(Kitchen_Table);

        ImageView dj = new ImageView(this);
        dj.setImageResource(R.drawable.dj);
        dj.setContentDescription("dj");
        dj.setOnClickListener(view -> {selectedObject = Uri.parse("dj.sfb");});
        gallery.addView(dj);

        ImageView desk_set = new ImageView(this);
        desk_set.setImageResource(R.drawable.furniture);
            desk_set.setContentDescription("desk set");
        desk_set.setOnClickListener(view -> {selectedObject = Uri.parse("desk_set.sfb");});
        gallery.addView(desk_set);


        ImageView table = new ImageView(this);
        table.setImageResource(R.drawable.desk);
        table.setContentDescription("table");
        table.setOnClickListener(view -> {selectedObject = Uri.parse("Table_01.sfb");});
        gallery.addView(table);
    }

    private void placeObject(ArFragment fragment, Anchor anchor, Uri model){
        ModelRenderable.builder()
                .setSource(fragment.getContext(), model)
                .build()
                .thenAccept(renderable -> addNodeToScene(fragment, anchor, renderable))
                .exceptionally((throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(throwable.getMessage())
                            .setTitle("Error!");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return null;
                }));

    }

    private void addNodeToScene(ArFragment fragment, Anchor anchor, Renderable renderable){
        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode node = new TransformableNode(fragment.getTransformationSystem());
        node.setRenderable(renderable);
        node.setParent(anchorNode);
        fragment.getArSceneView().getScene().addChild(anchorNode);
        node.select();
    }


}
