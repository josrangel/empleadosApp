package com.inovacionaplicada.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.inovacionaplicada.ConexionLocal;
import com.inovacionaplicada.DetalleEmpleadoActivity;
import com.inovacionaplicada.R;
import com.inovacionaplicada.entity.Empleado;
import com.inovacionaplicada.agregarElemento.NuevoEmpleadoActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class EmpleadoAdapter extends RecyclerView.Adapter<EmpleadoAdapter.ViewHolder> {
    ArrayList<Empleado> listEmpleados;
    Context context;

    public EmpleadoAdapter(ArrayList<Empleado> listEmpleados, Context context) {
        super();
        this.listEmpleados = listEmpleados;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_empleado, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.asignarDatos(listEmpleados.get(position));

    }

    @Override
    public int getItemCount() {
        return listEmpleados.size();
    }

    public void notificaCambios() {
        //notifyDataSetChanged();
        ConexionLocal con=new ConexionLocal();
        this.listEmpleados= con.listaEmpleados(context);
        notifyItemRemoved(getItemCount());
        Toast.makeText(context,"Se elimino el registro",Toast.LENGTH_SHORT).show();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombre, tvArea;
        RelativeLayout relativeLayoutContenedor;
        FloatingActionButton btnElimina, btnEdita;
        View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvArea = itemView.findViewById(R.id.tvArea);
            relativeLayoutContenedor = itemView.findViewById(R.id.relativeLayoutContenedor);
            btnElimina = itemView.findViewById(R.id.btnElimina);
            btnEdita = itemView.findViewById(R.id.btnEdita);
            this.itemView = itemView;
        }

        public void asignarDatos(Empleado datosEmpleado) {
            final int empleadoId = datosEmpleado.getEmpleado_id();
            tvNombre.setText(datosEmpleado.getEmpleado_nombre()+" "+datosEmpleado.getEmpleado_apellidoPaterno()+" "+datosEmpleado.getEmpleado_apellidoMaterno());
            String area = "Software";
            if (datosEmpleado.getEmpleado_area() == 1) {
                area = "consultoria";
            }
            tvArea.setText(area);


            relativeLayoutContenedor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent verDetalle = new Intent(itemView.getContext(), DetalleEmpleadoActivity.class);
                    verDetalle.putExtra("empleadoId", empleadoId);
                    itemView.getContext().startActivity(verDetalle);
                }
            });
            btnEdita.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent verDetalle = new Intent(itemView.getContext(), NuevoEmpleadoActivity.class);
                    verDetalle.putExtra("empleadoId", empleadoId);
                    itemView.getContext().startActivity(verDetalle);
                }
            });

            btnElimina.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(itemView.getContext());
                    dialogo.setTitle("Advertencia");
                    dialogo.setMessage("¿Esta seguro que desea eliminar este registro? \n Esta accion no podra revertirse");
                    //dialogo.setCancelable(false);
                    dialogo.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            ConexionLocal con = new ConexionLocal();
                            con.eliminaEmpleado(itemView.getContext(), empleadoId);
                            notificaCambios();
                            //notifyAll();
                        }
                    });
                    dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            //dialogo.
                        }
                    });
                    dialogo.show();

                }

            });
        }
    }

}