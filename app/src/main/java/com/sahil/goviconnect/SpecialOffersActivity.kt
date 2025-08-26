package com.sahil.goviconnect

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class SpecialOffersActivity : AppCompatActivity() {

    enum class Category { ALL, SUBSIDY, INSURANCE, EQUIPMENT, ADVISORY, OTHER }

    data class Offer(
        val emoji: String,
        val title: String,
        val subtitle: String,
        val badge: String,      // e.g., "-15%" or "BOGO"
        val code: String,       // e.g., "FERTI15"
        val category: Category
    )

    private lateinit var offersContainer: LinearLayout
    private lateinit var trendingContainer: LinearLayout
    private lateinit var chipsRow: LinearLayout
    private lateinit var etSearch: EditText
    private lateinit var btnFilter: Button
    private lateinit var btnMyCoupons: Button
    private lateinit var btnBack: TextView

    // ---------- sample data ----------
    private val allOffers = listOf(
        Offer("🌾", "Seed Bundle Discount", "Up to 20% off certified seed packs", "-20%", "SEED20", Category.SUBSIDY),
        Offer("💧", "Drip Irrigation Kit", "Save water with smart drip systems", "-15%", "DRIP15", Category.EQUIPMENT),
        Offer("🧪", "Soil Test Voucher", "Free test with any fertilizer purchase", "FREE", "SOILFREE", Category.ADVISORY),
        Offer("🚜", "Equipment Rental", "Weekend tractor rates slashed", "BOGO", "TRACTOR2", Category.EQUIPMENT),
        Offer("☔", "Monsoon Cover", "10% off crop insurance add-ons", "-10%", "RAIN10", Category.INSURANCE),
        Offer("🧑‍⚕️", "Agri-Clinic Consult", "First consult at a special price", "-25%", "CLINIC25", Category.ADVISORY),
        Offer("🪓", "Tools Combo", "Hand tools combo for harvest season", "-12%", "HARV12", Category.OTHER)
    )

    private val trending = listOf(
        Offer("💧", "Drip Irrigation Kit", "Smart drip systems", "-15%", "DRIP15", Category.EQUIPMENT),
        Offer("☔", "Monsoon Cover", "Crop insurance add-ons", "-10%", "RAIN10", Category.INSURANCE),
        Offer("🌾", "Seed Bundle Discount", "Certified seed packs", "-20%", "SEED20", Category.SUBSIDY)
    )

    private var selectedCategory: Category = Category.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_special_offers)

        bindViews()
        wireTopBar()
        buildChips()
        renderTrending(trending)
        renderAll(listToRender())

        // Search: hit enter to apply
        etSearch.setOnEditorActionListener { _, _, _ ->
            renderAll(listToRender())
            true
        }

        btnFilter.setOnClickListener { toast("Filter options (coming soon)") }
        btnMyCoupons.setOnClickListener { toast("Opening My Coupons…") }
    }

    private fun bindViews() {
        offersContainer = findViewById(R.id.offersContainer)
        trendingContainer = findViewById(R.id.trendingContainer)
        chipsRow = findViewById(R.id.chips)
        etSearch = findViewById(R.id.etSearch)
        btnFilter = findViewById(R.id.btnFilter)
        btnMyCoupons = findViewById(R.id.btnMyCoupons)
        btnBack = findViewById(R.id.btnBack)
    }

    private fun wireTopBar() {
        btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    // ---------- category chips ----------
    private fun buildChips() {
        chipsRow.removeAllViews()
        val cats = listOf(
            "All" to Category.ALL,
            "Subsidy" to Category.SUBSIDY,
            "Insurance" to Category.INSURANCE,
            "Equipment" to Category.EQUIPMENT,
            "Advisory" to Category.ADVISORY,
            "Other" to Category.OTHER
        )
        cats.forEach { (label, cat) ->
            chipsRow.addView(makeChip(label, cat))
        }
        highlightSelectedChip() // initial
    }

    private fun makeChip(text: String, cat: Category): TextView {
        val tv = TextView(this).apply {
            this.text = text
            textSize = 13f
            setPadding(dp(14), dp(8), dp(14), dp(8))
            setTextColor(resColor(R.color.text_primary))
            background = bgChip(isActive = cat == selectedCategory)
        }
        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        lp.rightMargin = dp(8)
        tv.layoutParams = lp

        tv.setOnClickListener {
            selectedCategory = cat
            highlightSelectedChip()
            renderAll(listToRender())
        }
        return tv
    }

    private fun highlightSelectedChip() {
        for (i in 0 until chipsRow.childCount) {
            val v = chipsRow.getChildAt(i)
            if (v is TextView) {
                val label = v.text.toString().uppercase(Locale.getDefault())
                val cat = Category.values().firstOrNull { it.name == label } ?: Category.ALL
                v.background = bgChip(isActive = cat == selectedCategory || (selectedCategory == Category.ALL && label == "ALL"))
            }
        }
    }

    private fun bgChip(isActive: Boolean): android.graphics.drawable.GradientDrawable {
        return android.graphics.drawable.GradientDrawable().apply {
            cornerRadius = dp(18).toFloat()
            setColor(if (isActive) resColor(R.color.green_primary) else 0xFFF0F0F0.toInt())
        }
    }

    // ---------- renderers ----------
    private fun renderTrending(list: List<Offer>) {
        trendingContainer.removeAllViews()
        list.forEach { trendingContainer.addView(makeSmallCard(it)) }
    }

    private fun renderAll(list: List<Offer>) {
        offersContainer.removeAllViews()
        if (list.isEmpty()) {
            offersContainer.addView(TextView(this).apply {
                text = "No offers found."
                setTextColor(resColor(R.color.text_muted))
                textSize = 14f
                gravity = Gravity.CENTER
                setPadding(0, dp(16), 0, dp(16))
            })
            return
        }
        list.forEach { offersContainer.addView(makeBigCard(it)) }
    }

    // ---------- filtering ----------
    private fun listToRender(): List<Offer> {
        val q = etSearch.text.toString().trim().lowercase(Locale.getDefault())
        val base = if (selectedCategory == Category.ALL) allOffers else allOffers.filter { it.category == selectedCategory }
        return if (q.isEmpty()) base else base.filter {
            it.title.lowercase().contains(q) || it.subtitle.lowercase().contains(q) || it.code.lowercase().contains(q)
        }
    }

    // ---------- card factories ----------
    private fun makeSmallCard(of: Offer): LinearLayout {
        val p = dp(12)
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(resources.getColor(android.R.color.white))
            elevation = dp(1).toFloat()
            layoutParams = LinearLayout.LayoutParams(dp(160), ViewGroup.LayoutParams.WRAP_CONTENT).also {
                it.rightMargin = dp(10)
            }
            setPadding(p, p, p, p)

            addView(TextView(this@SpecialOffersActivity).apply {
                text = of.emoji
                textSize = 28f
            })
            addView(TextView(this@SpecialOffersActivity).apply {
                text = of.title
                setTextColor(resColor(R.color.text_primary))
                textSize = 14f
                setTypeface(typeface, Typeface.BOLD)
            })
            addView(TextView(this@SpecialOffersActivity).apply {
                text = of.badge
                setTextColor(resColor(R.color.white))
                textSize = 12f
                setPadding(dp(8), dp(4), dp(8), dp(4))
                setBackgroundColor(resColor(R.color.card_orange))
                val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                lp.topMargin = dp(6)
                layoutParams = lp
            })
            setOnClickListener { toast("${of.title} → Code ${of.code}") }
        }
    }

    private fun makeBigCard(of: Offer): LinearLayout {
        val pSm = dp(8); val pMd = dp(12)
        val card = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(resources.getColor(android.R.color.white))
            elevation = dp(2).toFloat()
            layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT).also {
                it.topMargin = dp(12)
            }
            setPadding(pMd, pMd, pMd, pMd)
        }

        // header row
        val row = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }
        val icon = TextView(this).apply { text = of.emoji; textSize = 26f }
        val titleBox = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f).also { it.leftMargin = pSm }
        }
        val tvTitle = TextView(this).apply {
            text = of.title
            setTextColor(resColor(R.color.text_primary))
            textSize = 16f
            setTypeface(typeface, Typeface.BOLD)
        }
        val tvSub = TextView(this).apply {
            text = of.subtitle
            setTextColor(resColor(R.color.text_muted))
            textSize = 13f
        }
        val badge = TextView(this).apply {
            text = of.badge
            setTextColor(resColor(R.color.white))
            textSize = 12f
            setPadding(pSm, dp(4), pSm, dp(4))
            setBackgroundColor(resColor(R.color.card_orange))
        }
        titleBox.addView(tvTitle); titleBox.addView(tvSub)
        row.addView(icon); row.addView(titleBox); row.addView(badge)

        // code row
        val codeRow = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.START or Gravity.CENTER_VERTICAL
            layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT).also {
                it.topMargin = pSm
            }
        }
        val tvCode = TextView(this).apply {
            text = "Code: ${of.code}"
            setTextColor(resColor(R.color.text_primary))
            textSize = 14f
        }
        val btnCopy = Button(this).apply {
            text = "Copy"
            layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT).also {
                it.leftMargin = pSm
            }
            setOnClickListener {
                copyToClipboard("OfferCode", of.code)
                toast("Copied ${of.code}")
            }
        }
        codeRow.addView(tvCode); codeRow.addView(btnCopy)

        // footer
        val footer = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.END
            layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT).also {
                it.topMargin = pSm
            }
        }
        val btnApply = Button(this).apply {
            text = "Apply Offer"
            setOnClickListener { toast("Applying ${of.code}…") }
        }
        footer.addView(btnApply)

        card.addView(row)
        card.addView(codeRow)
        card.addView(footer)
        return card
    }

    // ---------- utils ----------
    private fun dp(v: Int): Int = (v * resources.displayMetrics.density).toInt()
    private fun resColor(id: Int): Int = resources.getColor(id)
    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    private fun copyToClipboard(label: String, text: String) {
        val cm = getSystemService(CLIPBOARD_SERVICE) as android.content.ClipboardManager
        cm.setPrimaryClip(android.content.ClipData.newPlainText(label, text))
    }
}
