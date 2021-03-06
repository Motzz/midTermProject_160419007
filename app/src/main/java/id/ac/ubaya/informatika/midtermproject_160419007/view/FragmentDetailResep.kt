package id.ac.ubaya.informatika.midtermproject_160419007.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.midtermproject_160419007.R
import id.ac.ubaya.informatika.midtermproject_160419007.model.Global
import id.ac.ubaya.informatika.midtermproject_160419007.util.loadImage
import id.ac.ubaya.informatika.midtermproject_160419007.viewModel.DetailListResep
import kotlinx.android.synthetic.main.fragment_detail_resep.*

class FragmentDetailResep : Fragment() {
    private lateinit var viewModel: DetailListResep
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_resep, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments!=null)
        {
            Global.foodName=FragmentDetailResepArgs.fromBundle(requireArguments()).foodName
        }

        viewModel = ViewModelProvider(this).get(DetailListResep::class.java)
        viewModel.fetch()
        observeViewModel()

        btnBackDetailRes.setOnClickListener {
            val action = FragmentDetailResepDirections.actionFragmentDetailResepToItemHome()
            //hanya nav controller yang bisa mengontrol host nya
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun observeViewModel() {
        viewModel.resepLD.observe(viewLifecycleOwner, Observer {
            txtNameFood.text=it.name.toString()
            txtIngridients.text= it.ingredients!!.joinToString("\n\n")
            txtSteps.text=it.steps!!.joinToString("\n\n")
            imageViewDetailResep.loadImage(it.imageURL.toString(),progressBarDetailResep)

        })
    }


}